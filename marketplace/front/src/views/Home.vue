<template>
<div>
    <v-img
      :max-height="450"
      :src="require('@/assets/pexels-photo.jpg')"
      class="white--text"
      gradient="to right, rgba(5, 11, 31, .5), rgba(5, 11, 31, .2)"
    >
    <template v-slot:placeholder>
        <v-row
          class="fill-height ma-0"
          align="center"
          justify="center"
        >
          <v-progress-linear indeterminate absolute top color="light-blue darken-4"></v-progress-linear>
        </v-row>
      </template>
        <v-responsive
          class="d-flex align-center ma-5 pa-5"
          height="100%"
          max-width="700"
          width="100%"
        >
          <h1>Reactive Board</h1>

            <p>Spring을 백엔드서버(Business Layer),<br>Vue.js를 프론트엔드(Presentation Layer)로 개발한 반응형 게시판.</p>

          <div
            :class="$vuetify.breakpoint.smAndDown ? 'flex-column align-start' : 'align-center'"
            class="d-flex flex-wrap"
          >
            <v-btn class="ma-3" tile color="light-blue darken-4" dark @click="launch">
              Launch Bashpound Board
            </v-btn>

          </div>
        </v-responsive>
    </v-img>
  </div>
</template>

<script>

export default {
  name: 'Home',
  components: {
  },
  computed: {
    maxHeight () {
      const height = this.$vuetify.breakpoint.mdAndUp ? '100vh' : '50vh'
      return `calc(0.6*(${height} - ${this.$vuetify.application.top})px)`
    }
  },
  methods: {
    launch () {
      this.$router.push('BoardView')
      this.$store.commit('launch', true)
      this.$bus.emit('board')
    }
  }
}
</script>

<style scoped>
</style>
