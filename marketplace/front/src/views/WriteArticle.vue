<template>
  <div>
    <form class="col-6 border mx-auto">
      <div class="form-group row border">
         <label for="Subject" class="display-5">글쓰기</label>
          <input type="text" class="form-control mx-3 rounded-0" id="Subject" placeholder="제목" />
      </div>
      <CKEditor />
      <div class="form-group row col-5">
  <div class="text-center">
    <v-btn class="ma-2" tile color="indigo" dark>Tile Button</v-btn>
    <v-btn class="ma-2" tile outlined color="success">
      <v-icon left>mdi-pencil</v-icon> Edit
    </v-btn>
    <v-btn class="ma-2" tile large color="teal" icon>
      <v-icon>mdi-vuetify</v-icon>
    </v-btn>
  </div>
      </div>
    </form>
  </div>
</template>

<script>
import CKEditor from '@/components/CKEditor.vue'

export default {
  name: 'WriteArticle',
  components: {
    CKEditor
  },
  data () {
    return {
      loader: null,
      loading: false,
      loading2: false,
      loading3: false,
      loading4: false,
      loading5: false
    }
  },
  watch: {
    loader () {
      const l = this.loader
      this[l] = !this[l]

      setTimeout(() => (this[l] = false), 3000)

      this.loader = null
    }
  }
}
</script>

<style scoped>
.form-control:focus {
  border-color: rgb(58, 98, 230);
  box-shadow: inset 0 0px 0px rgba(0, 0, 0, 0.075), 0 0 0px rgba(255, 0, 0, 0.6);
}
</style>
