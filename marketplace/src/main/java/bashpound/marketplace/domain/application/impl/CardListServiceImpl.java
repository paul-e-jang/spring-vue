package bashpound.marketplace.domain.application.impl;

import org.springframework.stereotype.Service;

import bashpound.marketplace.domain.application.CardListService;
import bashpound.marketplace.domain.application.commands.AddCardListCommand;
import bashpound.marketplace.domain.application.commands.ChangeCardListPositionsCommand;
import bashpound.marketplace.domain.common.event.DomainEventPublisher;
import bashpound.marketplace.domain.model.board.BoardId;
import bashpound.marketplace.domain.model.cardlist.CardList;
import bashpound.marketplace.domain.model.cardlist.CardListRepository;
import bashpound.marketplace.domain.model.cardlist.events.CardListAddedEvent;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CardListServiceImpl implements CardListService {

  private CardListRepository cardListRepository;
  private DomainEventPublisher domainEventPublisher;

  public CardListServiceImpl(CardListRepository cardListRepository,
                             DomainEventPublisher domainEventPublisher) {
    this.cardListRepository = cardListRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  @Override
  public List<CardList> findByBoardId(BoardId boardId) {
    return cardListRepository.findByBoardId(boardId);
  }

  @Override
  public CardList addCardList(AddCardListCommand command) {
    CardList cardList = CardList.create(command.getBoardId(),
      command.getUserId(), command.getName(), command.getPosition());

    cardListRepository.save(cardList);
    domainEventPublisher.publish(new CardListAddedEvent(cardList, command));
    return cardList;
  }

  @Override
  public void changePositions(ChangeCardListPositionsCommand command) {
    cardListRepository.changePositions(command.getCardListPositions());
  }
}
