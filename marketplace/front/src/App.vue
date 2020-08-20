<template>
  <v-app id="main">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <v-navigation-drawer
    v-model="drawer"
    :clipped="$vuetify.breakpoint.lgAndUp"
    color="blue-grey lighten-5"
    app
    >
      <v-list dense>
        <v-list-item two-line :class="miniVariant && 'px-0'" id="namecard">
            <a href="http://github.com/bashpound/">
            <v-list-item-avatar>
              <img src="./assets/logo.jpg">
            </v-list-item-avatar>
            </a>
            <v-list-item-content>
              <v-list-item-title>Project Explorer</v-list-item-title>
              <v-list-item-subtitle>Bashpound Board</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-divider />
        <v-subheader>Menu</v-subheader>
      <v-list-item-group v-model="item" color="primary">
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
          @click="$router.push(item.r)"
          :disabled="!Boolean(item.cons)"
        >
          <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title v-text="item.text"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>

      </v-list>
    </v-navigation-drawer>

        <v-app-bar :clipped-left="$vuetify.breakpoint.lgAndUp" app dense dark :color="themeColor" elevation="0" class="border">

     <v-scroll-x-transition>
      <v-app-bar-nav-icon :disabled="!activated" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      </v-scroll-x-transition>
      <v-scroll-x-transition>
      <v-toolbar-title style="width: 300px" class="ml-0 pl-4">
        <router-link to="/"><img src="./assets/logodark.svg" height="25" class="mx-3" /></router-link>
      </v-toolbar-title>
      </v-scroll-x-transition>
      <v-text-field
        flat
        dense
        clearable
        solo-inverted
        hide-details
        prepend-inner-icon="mdi-magnify"
        label="검색어를 입력하세요."
        class="hidden-sm-and-down"
      ></v-text-field>
      <v-spacer></v-spacer>
      <AppMenu />
      <Register v-if="!logged" />
      <Login v-if="!logged" />
      <Avatar v-if="logged" />
    </v-app-bar>

    <v-main>
      <v-container class="pa-0 ma-0" fluid>
        <v-fade-transition>
            <router-view />
        </v-fade-transition>
      </v-container>
    </v-main>
      <v-footer
    padless
    app
    dark
  >
    <v-card
      flat
      tile
      class="text-center flex"
      :color="themeColor"
    >
      <v-card-text class="white--text py-1">
        <v-btn
          v-for="item in footer"
          :key="item.icon"
          class="mx-4"
          icon
          :href="item.href"
        >
          <v-icon size="24px">{{ item.icon }}</v-icon>
        </v-btn>
      </v-card-text>

      <v-divider class="py-0 my-0"></v-divider>

      <v-card-text class="white--text py-1">
        {{ new Date().getFullYear() }} — <strong> Bashpound All rights reserved</strong>
      </v-card-text>
    </v-card>
  </v-footer>
  </v-app>
</template>

<script>
import Register from '@/components/Register'
import Login from '@/components/Login'
import AppMenu from '@/components/AppMenu'
import Avatar from '@/components/Avatar'

export default {
  created () {
    document.title = 'Projects'
  },
  props: {
    source: String
  },
  data: () => ({
    color: 'indigo lighten-1',
    dialog: false,
    drawer: false,
    showSNS: true,
    footer: [
      { icon: 'mdi-github', href: 'https://github.com/bashpound' },
      { icon: 'mdi-linkedin', href: 'https://www.linkedin.com/in/eunhack-jang-5698b597/' },
      { icon: 'mdi-adobe-acrobat', href: '' },
      { icon: 'mdi-email', href: 'mailto:eunhackjang@gmail.com' }
    ],
    nav: true
  }),
  components: {
    Register,
    AppMenu,
    Avatar,
    Login
  },
  computed: {
    activated () {
      return this.$store.getters.launched
    },
    themeColor () {
      return this.activated ? 'light-blue darken-4' : 'primary darken-4'
    },
    logged () {
      return Boolean(this.$store.getters.logged)
    },
    items () {
      return [
        { icon: 'mdi-history', text: 'Recent views', r: 'BoardView', cons: 'false' },
        { text: 'Write Article', icon: 'mdi-pencil-plus-outline', r: 'WriteArticle', cons: this.logged }
      ]
    }
  },
  methods: {
    draw () {
      this.drawer = true
    },
    undraw () {
      this.drawer = false
    }
  },
  watch: {
    '$route' (to, from) {
      if (to.name === 'BoardView' && from.name === 'Home') {
        setTimeout(() => {
          this.draw()
        }, 300)
      }
      if (to.name === 'Home' && from.name === 'BoardView') {
        this.$store.commit('launch', false)
        setTimeout(() => {
          this.undraw()
        }, 300)
      }
    }
  }
}
</script>

<style scoped>
#main {
  font-family: 'Roboto', 'Noto Sans KR', sans-serif;
}
a { text-decoration:none !important } a:hover { text-decoration:none !important }
</style>
