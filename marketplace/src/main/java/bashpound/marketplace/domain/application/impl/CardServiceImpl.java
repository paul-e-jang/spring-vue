package bashpound.marketplace.domain.application.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import bashpound.marketplace.domain.application.CardService;
import bashpound.marketplace.domain.application.commands.*;
import bashpound.marketplace.domain.common.event.DomainEventPublisher;
import bashpound.marketplace.domain.model.activity.Activity;
import bashpound.marketplace.domain.model.activity.ActivityRepository;
import bashpound.marketplace.domain.model.activity.CardActivities;
import bashpound.marketplace.domain.model.attachment.Attachment;
import bashpound.marketplace.domain.model.attachment.AttachmentManagement;
import bashpound.marketplace.domain.model.attachment.AttachmentRepository;
import bashpound.marketplace.domain.model.attachment.events.CardAttachmentAddedEvent;
import bashpound.marketplace.domain.model.board.BoardId;
import bashpound.marketplace.domain.model.card.Card;
import bashpound.marketplace.domain.model.card.CardId;
import bashpound.marketplace.domain.model.card.CardRepository;
import bashpound.marketplace.domain.model.card.events.CardAddedEvent;
import bashpound.marketplace.domain.model.card.events.CardDescriptionChangedEvent;
import bashpound.marketplace.domain.model.card.events.CardTitleChangedEvent;
import bashpound.marketplace.domain.model.cardlist.CardList;
import bashpound.marketplace.domain.model.cardlist.CardListRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CardServiceImpl implements CardService {

  private CardRepository cardRepository;
  private CardListRepository cardListRepository;
  private ActivityRepository activityRepository;
  private AttachmentManagement attachmentManagement;
  private AttachmentRepository attachmentRepository;
  private DomainEventPublisher domainEventPublisher;

  public CardServiceImpl(CardRepository cardRepository,
                         CardListRepository cardListRepository,
                         ActivityRepository activityRepository,
                         AttachmentRepository attachmentRepository,
                         AttachmentManagement attachmentManagement,
                         DomainEventPublisher domainEventPublisher) {
    this.cardRepository = cardRepository;
    this.cardListRepository = cardListRepository;
    this.activityRepository = activityRepository;
    this.attachmentManagement = attachmentManagement;
    this.attachmentRepository = attachmentRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  @Override
  public List<Card> findByBoardId(BoardId boardId) {
    return cardRepository.findByBoardId(boardId);
  }

  @Override
  public Card findById(CardId cardId) {
    return cardRepository.findById(cardId);
  }

  @Override
  public List<Activity> findCardActivities(CardId cardId) {
    return activityRepository.findCardActivities(cardId);
  }

  @Override
  public List<Attachment> getAttachments(CardId cardId) {
    return attachmentRepository.findAttachments(cardId);
  }

  @Override
  public Card addCard(AddCardCommand command) {
    CardList cardList = cardListRepository.findById(command.getCardListId());
    Assert.notNull(cardList, "Card list must not be null");

    Card card = Card.create(cardList, command.getUserId(), command.getTitle(), command.getPosition());
    cardRepository.save(card);
    domainEventPublisher.publish(new CardAddedEvent(card, command));
    return card;
  }

  @Override
  public void changePositions(ChangeCardPositionsCommand command) {
    cardRepository.changePositions(command.getCardPositions());
  }

  @Override
  public void changeCardTitle(ChangeCardTitleCommand command) {
    Assert.notNull(command, "Parameter `command` must not be null");

    Card card = findCard(command.getCardId());
    String oldTitle = card.getTitle();
    card.changeTitle(command.getTitle());
    cardRepository.save(card);
    domainEventPublisher.publish(new CardTitleChangedEvent(card, oldTitle, command));
  }

  @Override
  public void changeCardDescription(ChangeCardDescriptionCommand command) {
    Assert.notNull(command, "Parameter `command` must not be null");

    Card card = findCard(command.getCardId());
    String oldDescription = card.getDescription();
    card.changeDescription(command.getDescription());
    cardRepository.save(card);
    domainEventPublisher.publish(new CardDescriptionChangedEvent(card, oldDescription, command));
  }

  @Override
  public Activity addComment(AddCardCommentCommand command) {
    Assert.notNull(command, "Parameter `command` must not be null");

    Card card = findCard(command.getCardId());
    Activity cardActivity = CardActivities.from(
      card, command.getUserId(), command.getComment(), command.getIpAddress());

    activityRepository.save(cardActivity);
    // No need to publish a domain event because the
    return cardActivity;
  }

  @Override
  public Attachment addAttachment(AddCardAttachmentCommand command) {
    Assert.notNull(command, "Parameter `command` must not be null");

    Card card = findCard(command.getCardId());
    Attachment attachment = attachmentManagement.save(
      command.getCardId(), command.getFile(), command.getUserId());

    if (!card.hasCoverImage() && attachment.isThumbnailCreated()) {
      card.addCoverImage(attachment.getFilePath());
      cardRepository.save(card);
    }

    domainEventPublisher.publish(new CardAttachmentAddedEvent(card, attachment, command));
    return attachment;
  }

  private Card findCard(CardId cardId) {
    Card card = cardRepository.findById(cardId);
    Assert.notNull(card, "Card of id " + card + " must exist");
    return card;
  }
}
