<template>
    <v-menu transition="scale-transition" origin="top center">
      <template v-slot:activator="{ on, attrs }">
      <v-btn icon large v-bind="attrs" v-on="on">
        <v-avatar size="32px" item>
          <v-img src="../assets/logo.jpg" alt="Bashpound"></v-img>
        </v-avatar>
      </v-btn>
      </template>
    <v-list flat>
      <v-list-item-group v-model="item">
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
          @click="sessionclear"
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
    </v-menu>
</template>

<script>
import authenticationService from '@/services/authentication'

export default {
  data: () => ({
    item: 1,
    items: [
      { text: '로그아웃', icon: 'mdi-account-off' }
    ]
  }),
  props: {
    username: String
  },
  methods: {
    sessionClear () {
      authenticationService.logOut().then(() => {
        authenticationService.fetchuser()
        this.$bus.$on('logout', data => {
          this.$store.commit('updateMyData', data)
        })
      })
    }
  }
}
</script>
