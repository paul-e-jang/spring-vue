<template>
    <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>

</template>

<script>
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import Vue from 'vue'
import CKEditor from '@ckeditor/ckeditor5-vue'

Vue.use(CKEditor)

export default {
  name: 'CKEditor',
  data () {
    return {
      editor: ClassicEditor,
      editorData: '',
      editorConfig: ''
    }
  }
}
</script>

<style>
.ck-editor__editable {
       min-height: 500px;
}
</style>
