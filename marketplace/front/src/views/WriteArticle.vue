<template>
<v-container>
  <v-row justify="center" class="d-flex align-stretch">
      <v-col cols="10">
        <v-card outlined class="pa-5">
          <v-card-title>글쓰기</v-card-title>
          <div class="field-error" v-if="$v.form.subject.$dirty">
            <div class="col-12 alert alert-danger failed text-center" v-if="!$v.form.subject.required">제목을 입력하세요.</div>
            <div class="col-12 alert alert-danger failed text-center" v-if="!$v.form.subject.maxLength">제목은 최대 {{$v.form.subject.$params.maxLength.max}} 글자입니다.</div>
          </div>
          <div v-show="errorMessage" class="col-12 alert alert-danger failed text-center">{{ errorMessage }}</div>
    <form class="mx-auto" @submit.prevent="submitForm">
      <div class="form-group row">
        <input
          type="text"
          class="form-control mx-3 rounded-0"
          id="subject"
          placeholder="제목"
          v-model="form.subject"
        />
      </div>
      <ckeditor :editor="editor" v-model="form.content" :config="editorConfig" id="editor"></ckeditor>
      <div class="form-group row col-6 mx-auto">
        <div class="text-center mx-auto">
          <v-btn class="ma-2 mr-3" tile outlined color="success" type="submit">
            <v-icon left>mdi-pencil</v-icon>등록
          </v-btn>
          <v-btn v-model="warn" class="ma-2" tile outlined @click.stop="warn = true">
            <v-icon left>mdi-cancel</v-icon>취소
          </v-btn>
          <v-dialog v-model="warn" max-width="500">
            <v-card>
             <v-card-title class="headline"> 확인 </v-card-title>
                <v-card-text>
                  되돌릴 수 없습니다. 취소하시겠습니까?
                 </v-card-text>
          <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="grey darken-1"
            text
            @click="warn = false"
          >
            취소
          </v-btn>

          <v-btn
            color="red darken-1"
            text
            @click="warn = false, $router.push('BoardView')"
          >
            확인
          </v-btn>
        </v-card-actions>
        </v-card>
          </v-dialog>
        </div>
      </div>
      <v-sheet class="text-center">
      <b>**[DEBUG]**</b><br>
      폼 입력 바인딩 테스트<br>
      form.subject: {{ form.subject }}<br>
      form.content: {{ form.content }}<br>
      form.author: {{ form.author }}<br>
      form.boardname: {{ form.boardname }}
      </v-sheet>
    </form>
    </v-card>
  </v-col>
  </v-row>
  </v-container>
</template>

<script>
import { required, maxLength } from 'vuelidate/lib/validators'
import ArticleService from '@/services/article'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import Vue from 'vue'
import CKEditor from '@ckeditor/ckeditor5-vue'
import UploadAdapter from '@/utils/upload-adapter'

Vue.use(CKEditor)

export default {
  name: 'WriteArticle',
  data () {
    return {
      form: {
        subject: '',
        content: '',
        author: this.$store.state.user.name,
        boardname: 'default'
      },
      errorMessage: '',
      editor: ClassicEditor,
      editorConfig: {
        height: '500px',
        extraPlugins: [this.MyCustomUploadAdapterPlugin],
        language: 'ko'
      },
      warn: false,
      rules: [
        value => !value || value.size < 2000000 || 'Avatar size should be less than 2 MB!'
      ]
    }
  },
  components: {
    ckeditor: CKEditor.component
  },
  methods: {
    submitForm () {
      this.$v.$touch()
      if (this.$v.$invalid) {
        return
      }

      ArticleService.writeArticle(this.form)
        .then(() => {
          ArticleService.fetchArticles(this.form.boardname)
          this.$router.push({ path: 'BoardView' })
        })
        .catch((error) => {
          this.errorMessage = '게시글 등록에 실패했습니다.' + error.message
        })
    },
    goBack () {
      this.$router.go(-1)
    },
    MyCustomUploadAdapterPlugin (editor) {
      editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
        return new UploadAdapter(loader)
      }
    }
  },
  validations: {
    form: {
      subject: {
        required,
        maxLength: maxLength(100)
      }
    }
  }
}
</script>
<style>
.form-control:focus {
  border-color: rgb(58, 98, 230);
  box-shadow: inset 0
   0px 0px rgba(0, 0, 0, 0.075), 0 0 0px rgba(255, 0, 0, 0.6);
}
.ck-editor__editable {
    min-height: 500px;
}
</style>
