<template>
  <ul class="nav float-right">
    <li class="mr-3"><b>개발 메뉴</b></li>
    <li><router-link to="/WriteArticle" class="text-dark mr-3">글쓰기</router-link></li>
    <li><router-link to="/BoardView" class="text-dark mr-3" @click.prevent="fetchArticles">게시판 보기</router-link> </li>
    <li><router-link to="/Admin" class="text-dark mr-3">관리자 화면</router-link> </li>
  </ul>
</template>

<script>
import BoardService from '@/services/article'

export default {
  name: 'DevMenu',
  methods: {

  }
}
</script>
