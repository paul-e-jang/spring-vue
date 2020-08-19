<template>
  <v-sheet>
    <v-row justify="center" class="d-flex align-stretch">
      <draggable>
      <v-col cols="10" v-show="!articleView">
        <v-card id="boardlist" outlined class="pa-5 rounded-0">
          <v-card-title>
            Default 게시판
            <font color="red">[DEBUG] Test_usercode: {{ this.$store.getters.usercode }}</font>
            <v-spacer></v-spacer>
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="제목으로 검색"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
            :items="articles"
            :search="search"
            :sort-by="['created_date']"
            calculated-widths="true"
            :headers="headers"
            :loading="loading"
            :dense="dense"
            class="px-4"
          >
            <template v-slot:body="{ items }">
              <tbody>
                <tr v-for="(item, index) in items" :key="index">
                  <td>{{ index+1 }}</td>
                  <td class="text-left" @click.prevent="select(index)">
                    {{ item.subject }}
                    <span v-if="item.replies">[{{ item.replies }}]</span>
                  </td>
                  <td>{{ item.author }}</td>
                  <td>{{ dateConvert(item.createdDate) }}</td>
                  <td>{{ item.viewed }}</td>
                </tr>
              </tbody>
            </template>
            <template v-slot:progress>
              <v-progress-linear color="purple" :height="10" indeterminate></v-progress-linear>
            </template>
          </v-data-table>
        </v-card>
      </v-col>
      </draggable>
      <draggable>
      <v-col cols="10" v-show="articleView">
        <v-card class="mx-auto pa-5 rounded-0" id="boardview" outlined >
          <div class="text-right">[DEBUG] Seleted id: {{ selected.id }} <v-btn icon @click="articleView=false"> <v-icon> {{ mdiDelete }} </v-icon> </v-btn></div>
          <v-divider />
          <v-card-title class="text-left py-1">{{ boardname }} | {{ selected.subject}}</v-card-title>
          <v-subheader
            class="py-0 my-0"
          >작성자: {{ selected.author }} | 조회: {{ selected.viewed }} | 댓글: {{ replies.length }} 작성일: {{ selected.createdDate }}</v-subheader>
          <v-divider class="py-0 my-0" />
          <p class="text-left px-3 py-4" v-html="selected.content"></p>
          <v-divider />
          <div class="text-right mx-3">
            <v-btn text small class="mr-2" tile outlined>수정</v-btn>
            <v-btn text small class="mr-2" tile outlined>삭제</v-btn>
            <v-btn class="mr-2" tile outlined text small to="/WriteArticle">글쓰기</v-btn>
          </div>
          <v-divider />
          <div class="text-left px-2 mx-2">댓글 [ {{ replies.length }} ]</div>
          <v-divider />
          <v-simple-table dense class="mb-2">
            <template v-slot:default>
              <tbody>
                <tr v-for="(item, index) in replies" :key="index">
                  <td class="text-left" width="20%">{{ item.author }}</td>
                  <td class="text-left" width="70%">{{ item.content }}</td>
                  <td width="10%">{{ dateConvert(item.createdDate) }}</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
          <form @submit.prevent="writeReply">
            <v-textarea
              outlined
              class="pa-3"
              name="input-7-1"
              label="댓글"
              counter
              dense
              rows="3"
              v-model="re.content"
              required
              auto-grow
              clearable
              clear-icon="mdi-close"
            />
            <v-btn class="mr-2" tile outlined text small type="submit">댓글 입력</v-btn>
            <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
            <br />[DEBUG]
            <br />
            댓글작성자: {{ re.author }}
            <br />
            댓글내용: {{ re.content }}
            <br />
            댓글작성일: {{ re.createdDate }}
            <br />
            id: {{ re.articleId }}
            <br />
          </form>
        </v-card>
      </v-col>
      </draggable>
    </v-row>
  </v-sheet>
</template>

<script>
import BoardService from '@/services/article'
import DateParser from '@/utils/date-parser'
import draggable from 'vuedraggable'
import { mdiDelete } from '@mdi/js'

export default {
  name: 'BoardView',
  components: {
    draggable
  },
  created () {
    this.Fetch()
  },
  computed: {
    boardname () {
      return 'default'
    }
  },
  methods: {
    Fetch () {
      BoardService.fetchArticles('default')
      this.$bus.$on('articleLoad', (data) => {
        this.articles = data.articles
      })
    },
    dateConvert (date) {
      return DateParser.ParseRefactor(date)
    },
    select (index) {
      this.selected = this.articles[index]
      this.re.articleId = this.selected.id
      this.articleView = true
      console.log(this.selected)
      BoardService.viewedUpdate(this.re.articleId, 'pp')
      BoardService.repliesUpdate(this.re.articleId, this.replies.length)
      BoardService.fetchReplies(this.re.articleId)
      this.$bus.$on('replyLoad', (data) => {
        this.replies = data.replies
      })
      this.Fetch()
    },
    writeReply () {
      BoardService.writeReply(this.re)
        .then(() => {
          BoardService.fetchReplies(this.re.articleId)
          this.re.content = ''
          BoardService.repliesUpdate(this.re.articleId, this.replies.length)
        })
        .catch((error) => {
          this.errorMessage = '댓글 등록에 실패했습니다.' + error.message
        })
    }
  },
  data () {
    return {
      mdiDelete,
      articleView: false,
      search: '',
      height: 800,
      dense: true,
      loading: false,
      reply: '',
      errorMessage: '',
      headers: [
        {
          text: '번호',
          sortable: false,
          value: 'no',
          width: '10%'
        },
        {
          text: '제목',
          value: 'subject',
          sortable: false,
          width: '60%',
          align: 'center'
        },
        { text: '작성자', value: 'author', sortable: false, width: '10%' },
        { text: '작성일', value: 'createdDate', sortable: false, width: '10%' },
        { text: '조회', value: 'viewed', sortable: false, width: '10%' }
      ],
      articles: [],
      replies: [],
      allreplies: [],
      selected: {},
      re: {
        content: '',
        author: this.$store.getters.user,
        articleId: ''
      },
      /* desserts: 개발용 mock 데이터 */
      desserts: [
        {
          no: '1',
          subject: 'Frozen Yogurt',
          viewed: 159,
          content: 'tag applied<br>something<br>lorem',
          author: 'eunhackjang',
          createdDate: '22/35',
          id: '32'
        },
        {
          no: '2',
          subject: 'Frozen Yogurt',
          viewed: 159,
          content: 'tag applied<br>something<br>lorem',
          author: 'eunhackjang',
          createdDate: '22/35',
          id: '32'
        }
      ]
    }
  }
}
</script>

<style scoped>
#boardview,
#boardlist {
  min-height: 80vh;
}
#article-content {
  min-height: 80vh;
}
</style>
