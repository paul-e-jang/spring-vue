<template>
  <v-app id="app" class="container-fluid align-content-center">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,400;0,500;0,700;1,300;1,400;1,500&display=swap" rel="stylesheet">
    <Header />
    <v-main>
      <v-container fluid>
    <router-view/>
      </v-container>
    </v-main>
    <Footer />
  </v-app>
</template>

<style lang="scss">

#app {
  font-family: 'Roboto', 'Noto Sans KR', Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background: linear-gradient( to bottom, white 30%,#e7e7e79f 70%);
}

body.modal-open {
    overflow: auto;
}
body.modal-open[style] {
    padding-right: 0px !important;
}

.modal::-webkit-scrollbar {
    width: 0 !important; /*removes the scrollbar but still scrollable*/
    /* reference: http://stackoverflow.com/a/26500272/2259400 */
}
</style>

<script>
import Footer from '@/components/Footer.vue'
import Header from '@/components/Header.vue'
import authenticationService from './services/authentication'

export default {
  created () {
    authenticationService.fetchuser()
    this.$bus.$on('myDataFetched', data => {
      this.$store.commit('updateMyData', data)
    })
  },
  components: {
    Footer,
    Header
  }
}
</script>
