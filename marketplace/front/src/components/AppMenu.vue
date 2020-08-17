<template>
    <v-menu transition="scale-transition" origin="top center">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          dark
          icon
          v-bind="attrs"
          v-on="on"
        >
          <v-icon> mdi-apps </v-icon>
        </v-btn>
      </template>
      <v-list>
        <v-list-item v-for="n in 4" :key="n" @click="() => {}">
          <v-list-item-title v-text="'Something I Can do: ' + n"></v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
</template>

<script>
export default {
  data () {
    return {
    }
  }
}
</script>
