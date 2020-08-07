<template>
  <div>
    <form class="col-6 border mx-auto" @submit.prevent="submitForm">
      <div class="form-group row border">
        <label for="form.Subject"><h3>글쓰기</h3></label>
        <input
          type="text"
          class="form-control mx-3 rounded-0"
          id="subject"
          placeholder="제목"
          v-model="form.subject"
        />
        <div class="field-error" v-if="$v.form.subject.$dirty">
            <div class="error ml-3" v-if="!$v.form.subject.required">제목을 입력하세요.</div>
            <div class="error" v-if="!$v.form.subject.maxLength">제목은 최대 {{$v.form.subject.$params.maxLength.max}} 글자입니다.</div>
        </div>
      </div>
      <CKEditor />
      <div class="form-group row col-6 mx-auto">
        <div class="text-center mx-auto">
          <v-btn class="ma-2 mr-3" tile outlined color="success" type="submit">
            <v-icon left>mdi-pencil</v-icon>등록
          </v-btn>
          <v-btn v-b-modal.modal-center class="ma-2" tile outlined>
            <v-icon left>mdi-cancel</v-icon>취소
          </v-btn>
          <div>
          <b-modal id="modal-center" title="확인" centered content-class="shadow" @ok.prevent="goBack">
            <p class="my-2">취소 후 되돌릴 수 없습니다. 취소하시겠습니까?</p>
          </b-modal>
          </div>
        </div>
      </div>
      <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
    </form>
  </div>
</template>

<script>
import CKEditor from '@/components/CKEditor.vue'
import { required, maxLength } from 'vuelidate/lib/validators'
import Write from '@/services/write'

export default {
  name: 'WriteArticle',
  components: {
    CKEditor
  },
  data () {
    return {
      form: {
        subject: '',
        content: CKEditor.Editordata,
        author: this.$store.state.user.name
      },
      errorMessage: ''
    }
  },
  methods: {
    submitForm () {
      this.$v.$touch()
      if (this.$v.$invalid) {
        return
      }

      Write.DoWrite(this.form)
        .then(() => {
          this.$router.push({ path: 'BoardView' })
        })
        .catch((error) => {
          this.errorMessage = '게시글 등록에 실패했습니다.' + error.message
        })
    },
    goBack () {
      this.$router.go(-1)
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
<style scoped>
.form-control:focus {
  border-color: rgb(58, 98, 230);
  box-shadow: inset 0
   0px 0px rgba(0, 0, 0, 0.075), 0 0 0px rgba(255, 0, 0, 0.6);
}
.error{
  color: crimson;
  text-align: left;
  font-size: 14px;
}
</style>
