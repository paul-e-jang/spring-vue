<template>

      <v-navigation-drawer
        v-model="drawer"
        :color="color"
        :expand-on-hover="expandOnHover"
        :mini-variant="miniVariant"
        :right="right"
        :permanent="permanent"
        :src="bg"
        absolute
        dark
      >
        <v-list
          dense
          nav
          class="py-0"
        >
          <v-list-item two-line :class="miniVariant && 'px-0'">
            <a href="http://github.com/bashpound/">
            <v-list-item-avatar>
              <img src="../assets/logo.jpg">
            </v-list-item-avatar>
            </a>

            <v-list-item-content>
              <v-list-item-title>Bashpound</v-list-item-title>
              <v-list-item-subtitle>General Issue Desk</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>

          <v-divider></v-divider>

          <v-list-item
            v-for="item in items"
            :key="item.title"
            link
          >
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>

            <v-list-item-content
            @click="go">
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>

          </v-list-item>
        </v-list>
      </v-navigation-drawer>
</template>

<script>
import { mdiVuejs, mdiLanguagePython, mdiLeaf, mdiReact, mdiAngular, mdiNodejs, mdiDrawing } from '@mdi/js'

export default {
  name: 'NavDrawer',
  data () {
    return {
      drawer: true,
      items: [
        { title: 'Dashboard', icon: 'mdi-view-dashboard' },
        { title: 'Django', icon: mdiLanguagePython },
        { title: 'Vue.js', icon: mdiVuejs },
        { title: 'React.js', icon: mdiReact },
        { title: 'Angular.js', icon: mdiAngular },
        { title: 'Node.js', icon: mdiNodejs },
        { title: 'Spring', icon: mdiLeaf },
        { title: 'Architecture', icon: mdiDrawing }
      ],
      color: 'indigo lighten-1',
      right: false,
      permanent: true,
      miniVariant: false,
      expandOnHover: false,
      background: false
    }
  },
  computed: {
    bg () {
      return this.background ? 'https://cdn.vuetifyjs.com/images/backgrounds/bg-2.jpg' : undefined
    }
  },
  methods: {
    go: () => {
      this.$router.push('/blog')
    }
  }
}
</script>
