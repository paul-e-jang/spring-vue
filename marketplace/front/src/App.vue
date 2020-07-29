<template>
  <div id="app" class="container-fluid align-content-center">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <div id="header-wrapper"><Header /></div>
    <div id="router-wrapper"><router-view/></div>
    <div id="footer-wrapper" class="col-12"><Footer /></div>
  </div>
</template>

<style lang="scss">

#app {
  font-family: Avenir, Helvetica, Arial, 'Noto Sans KR', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background: linear-gradient( to bottom, white 15%,#f2f2f29f 85%);
}

</style>

<script>
import Footer from '@/components/Footer.vue'
import Header from '@/components/Header.vue'

export default {
  components: {
    Footer,
    Header
  }
}
</script>
