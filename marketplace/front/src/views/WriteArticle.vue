<template>
  <div>
    <form>
      <div class="form-group row col-5 my-auto py-0">
        <label for="staticEmail" class="col-sm-2 col-form-label align-self-center">작성자</label>
        <div class="col-sm-3">
          <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="name" />
        </div>
      </div>
      <div class="form-group row col-5 my-auto py-0">
        <label for="inputPassword" class="col-sm-2 col-form-label align-self-center">비밀번호</label>
        <div class="col-sm-3">
          <input type="password" class="form-control" id="inputPassword" placeholder="Password" />
        </div>
      </div>
      <div class="col-6">
      <CKEditor />
      </div>
      <div class="form-group row col-5">
      입력, 취소
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
  }
}
</script>
