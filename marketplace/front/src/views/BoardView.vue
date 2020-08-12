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
    <v-data-table :items="desserts" :search="search" :sort-by="['created_date']"
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
<v-card class="mx-auto" id="boardview" outlined>
   [DEBUG] Seleted author: {{ selected.author }}
   <v-divider />
    <v-card-title class="text-left py-1">
      {{ boardname }} | {{ selected.subject}}
    </v-card-title>
    <v-subheader class="py-0 my-0"> 작성자: {{ selected.author }} | 조회: {{ selected.viewed }} | 댓글: {{ desserts.length }} 작성일: {{ selected.createdDate }} </v-subheader>
    <v-divider class="py-0 my-0" />
    <p class="text-left px-3 py-4" v-html="selected.content">  </p>
    <v-divider />
    <div class="text-right mx-3">
      <v-btn text small class="mr-2" tile outlined>
        수정
      </v-btn>
      <v-btn text small v-b-modal.modal-center class="mr-2" tile outlined>
        삭제
      </v-btn>
      <v-btn class="mr-2" tile outlined text small>
        글쓰기
      </v-btn>
    </div>
    <v-divider />
    <div class="text-left px-2 mx-2"> 댓글 [ {{ desserts.length }} ] </div>
    <v-divider />
    <v-simple-table dense class="mb-2">
    <template v-slot:default>
      <tbody>
        <tr v-for="item in desserts" :key="item.name">
          <td class="text-left" width="20%">{{ item.author }}</td>
          <td class="text-left" width="70%"> {{ item.content }}</td>
          <td width="10">{{ item.createdDate }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
  <div>
   <v-text-field
            v-model="reply"
            solo
            label="Solo"
            clearable
   ></v-text-field>
  </div>
  </v-card>
  </div>
   </div>
</template>

<script>
import BoardService from '@/services/article'
import DateParser from '@/utils/date-parser'

export default {
  name: 'BoardView',
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
    },
    boardname () { return 'default' }
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
      this.selected = this.desserts[key - 1]
    }
  },
  data () {
    return {
      search: '',
      height: 800,
      dense: true,
      loading: true,
      reply: '',
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
      selected: '',
      desserts: [
        {
          no: '1',
          subject: 'Frozen Yogurt',
          viewed: 159,
          content: 'tag applied<br>something<br>lorem',
          author: 'eunhackjang',
          createdDate: '22/35'
        },
        {
          no: '2',
          subject: 'Frozen Yogurt',
          viewed: 159,
          content: 'tag applied<br>something<br>lorem',
          author: 'eunhackjang',
          createdDate: '22/35'
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
