<template>

    <v-app-bar :clipped-left="$vuetify.breakpoint.lgAndUp" app dense color="grey lighten-4" elevation="3" class="border">
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title style="width: 300px" class="ml-0 pl-4">
        <img src="../assets/svcLogoWithoutFrames.svg" height="25" class="mt-2 mx-3" >
      </v-toolbar-title>
      <v-text-field
        flat
        solo-inverted
        hide-details
        prepend-inner-icon="mdi-magnify"
        label="Search"
        class="hidden-sm-and-down"
      ></v-text-field>
      <v-spacer></v-spacer>
      <v-btn icon>
        <v-icon>mdi-apps</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>mdi-bell</v-icon>
      </v-btn>
      <v-btn icon large to="/">
        <v-avatar size="32px" item>
          <v-img src="../assets/logo.jpg" alt="Bashpound"></v-img>
        </v-avatar>
      </v-btn>
    </v-app-bar>

</template>

<script>

export default {
  name: 'Appbar'
}
</script>

<style>

</style>
