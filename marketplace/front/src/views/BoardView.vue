<template>
<div class="row">
  <div class="col-6">
  <v-card class="mx-auto border" id="boardview" outlined>
    <v-card-title>
      Default 게시판 <font color="red">[DEBUG] Test: {{ parceTest }} </font>
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table :items="articles" :search="search" :sort-by="['created_date']"
    calculated-widths="true" :headers="headers" :loading="loading" :dense="dense" class="px-4">
    <template v-slot:body="{ items }">
         <tbody>
          <tr v-for="item in items" :key="item.no">
            <td>{{ item.no }}</td>
            <td class="text-left" @click.prevent="selectArticle(item.no)">{{ item.subject }}</td>
            <td>{{ item.author }}</td>
            <td>{{ item.createdDate }}</td>
            <td>{{ item.viewed }}</td>
          </tr>
        </tbody>
     </template>
      <template v-slot:progress>
        <v-progress-linear color="purple" :height="10" indeterminate></v-progress-linear>
      </template>
    </v-data-table>
  </v-card>
</div>
<div class="col-6">
<v-card class="mx-auto border" id="boardview">
   [DEBUG] Seleted: {{ selected }}
   <v-divider />
    <v-card-title class="text-left">
      default | {{ selected }}
      <v-subheader> 작성자: author | 조회: viewed | 댓글: int 작성일: createdDate </v-subheader>
    </v-card-title>
    <v-divider />
    <p class="text-left" v-html="viewcontent">  </p>
    <v-divider />
    <div class="text-right mx-3">수정 | 삭제 | 글쓰기</div>
    <v-divider />
    <div class="text-left border px-3 mx-3"> 댓글 </div>
  </v-card>
  </div>
   </div>
</template>

<script>
import BoardService from '@/services/article'
import DateParser from '@/utils/date-parser'

export default {
  name: 'BoardView',
  boardname: 'default',
  created () {
    this.Fetch()
  },
  watch: {
    $route: 'Fetch'
  },
  computed: {
    today () { return this.timestamp.getDate() },
    parceTest () {
      return DateParser.ParceRefactor(this.time)
    }
  },
  methods: {
    Fetch () {
      BoardService.fetchArticles('default')
      this.$bus.$on('boardview', data => {
        this.articles = data.articles
        for (let i = this.articles.length - 1; i >= 0; i--) {
          this.articles[i].no = i + 1
          this.articles[i].createdDate = DateParser.ParceRefactor(this.articles[i].createdDate)
        }
      })
    },
    selectArticle (key) {
      this.selected = key - 1
      this.viewcontent = this.articles[this.selected].content
    }
  },
  data () {
    return {
      selected: '',
      search: '',
      height: 800,
      dense: true,
      loading: true,
      timestamp: new Date(),
      viewcontent: '',
      time: '2020-08-10T03:39:33.874+00:00',
      headers: [
        {
          text: '번호',
          sortable: false,
          value: 'no',
          width: '10%'
        },
        { text: '제목', value: 'subject', sortable: false, width: '60%', align: 'center' },
        { text: '작성자', value: 'author', sortable: false, width: '10%' },
        { text: '작성일', value: 'createdDate', sortable: false, width: '10%' },
        { text: '조회', value: 'viewed', sortable: false, width: '10%' }
      ],
      articles: [],
      desserts: [
        {
          no: '1',
          subject: 'Frozen Yogurt',
          viewed: 159,
          content: '<br>tag applied<br>something<br>lorem'
        }
      ]
    }
  }
}
</script>

<style scoped>
#boardview {
  min-height: 800px;
}
#article-content{
  min-height:500px;
}
</style>
