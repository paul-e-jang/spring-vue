<template>
  <v-card class="col-6 mx-auto border" id="boardview">
    <v-card-title>
      Default 게시판 <font color="red">[DEBUG] parceTest: {{ parceTest }} </font>
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
    calculated-widths="true" :headers="headers" :loading="loading">
    <template v-slot:body="{ items }">
         <tbody>
          <tr v-for="item in items" :key="item.no">
            <td>{{ item.no }}</td>
            <td class="text-left">{{ item.subject }}</td>
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
    }
  },
  data () {
    return {
      search: '',
      height: 800,
      dense: true,
      loading: true,
      timestamp: new Date(),
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
      articles: []
    }
  }
}
</script>

<style scoped>
#boardview {
  min-height: 800px;
}
</style>
