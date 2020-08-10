<template>
  <v-card class="col-6 mx-auto border" id="boardview">
    <v-card-title>
      Default 게시판
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
            <td>{{ item.created_date }}</td>
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

export default {
  name: 'BoardView',
  boardname: 'default',
  created () {
    BoardService.fetchArticles('default')
  },
  data () {
    return {
      search: '',
      height: 800,
      dense: true,
      loading: true,
      headers: [
        {
          text: '번호',
          sortable: false,
          value: 'no',
          width: '10%'
        },
        { text: '제목', value: 'subject', sortable: false, width: '60%', align: 'center' },
        { text: '작성자', value: 'author', sortable: false, width: '10%' },
        { text: '작성일', value: 'created_date', sortable: false, width: '10%' },
        { text: '조회', value: 'viewed', sortable: false, width: '10%' }
      ],
      articles: [
        {
          no: 1,
          subject: 159,
          author: 6.0,
          created_date: 24,
          viewed: 4.0
        },
        {
          no: 2,
          subject: 237,
          author: 9.0,
          created_date: 37,
          viewed: 4.3
        },
        {
          no: 3,
          subject: 262,
          author: 16.0,
          created_date: 23,
          viewed: 6.0
        },
        {
          no: 4,
          subject: 305,
          author: 3.7,
          created_date: 67,
          viewed: 4.3
        },
        {
          no: 5,
          subject: 356,
          author: 16.0,
          created_date: 49,
          viewed: 3.9
        },
        {
          no: 6,
          subject: 375,
          author: 0.0,
          created_date: 94,
          viewed: 0.0
        },
        {
          no: 7,
          subject: 392,
          author: 0.2,
          created_date: 98,
          viewed: 0
        },
        {
          no: 'Honeycomb',
          subject: 408,
          author: 3.2,
          created_date: 87,
          viewed: 6.5
        },
        {
          no: 'Donut',
          subject: 452,
          author: 25.0,
          created_date: 51,
          viewed: 4.9
        },
        {
          no: 'KitKat',
          subject: 518,
          author: 26.0,
          created_date: 65,
          viewed: 7
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
</style>
