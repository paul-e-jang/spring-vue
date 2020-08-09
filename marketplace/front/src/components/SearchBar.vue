<template>
  <form>
        <div class="inner-form">
          <div class="input-field second-wrap">
            <input id="search" type="text" placeholder="추천 상품" />
          </div>
          <div class="input-field third-wrap">
            <button class="btn-search" type="button">
              <svg class="svg-inline--fa fa-search fa-w-16" aria-hidden="true" data-prefix="fas" data-icon="search" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                <path fill="currentColor" d="M505 442.7L405.3 343c-4.5-4.5-10.6-7-17-7H372c27.6-35.3 44-79.7 44-128C416 93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c48.3 0 92.7-16.4 128-44v16.3c0 6.4 2.5 12.5 7 17l99.7 99.7c9.4 9.4 24.6 9.4 33.9 0l28.3-28.3c9.4-9.4 9.4-24.6.1-34zM208 336c-70.7 0-128-57.2-128-128 0-70.7 57.2-128 128-128 70.7 0 128 57.2 128 128 0 70.7-57.2 128-128 128z"></path>
              </svg>
            </button>
          </div>
        </div>
  </form>
</template>

<style scoped>
.inner-form {
  background: #fff;
  display: -ms-flexbox;
  display: flex;
  width: 100%;
  -ms-flex-pack: justify;
      justify-content: space-between;
  -ms-flex-align: center;
      align-items: center;
  box-shadow: 1px 1px 1px 1px rgba(206, 206, 206, 0.671);
  border-radius: 50px;
}

.inner-form .input-field {
  height: 50px;
}

.inner-form .input-field input {
  height: 100%;
  background: transparent;
  border: 0;
  display: block;
  width: 100%;
  padding: 10px 32px;
  font-size: 16px;
  color: #555;
}

.inner-form .input-field input.placeholder {
  color: darkslategrey;
  font-size: 16px;
}

.inner-form .input-field input:-moz-placeholder {
  color: #888;
  font-size: 16px;
}

.inner-form .input-field input::-webkit-input-placeholder {
  color: #888;
  font-size: 16px;
}

.inner-form .input-field input:hover, .inner-form .input-field input:focus {
  box-shadow: none;
  outline: 0;
  border-color: #fff;
}

.inner-form .input-field.second-wrap {
  -ms-flex-positive: 1;
      flex-grow: 1;
}

.inner-form .input-field.third-wrap {
  width: 74px;
}

.inner-form .input-field.third-wrap .btn-search {
  height: 100%;
  width: 100%;
  white-space: nowrap;
  color: #fff;
  border: 0;
  border-radius: 20px;
  cursor: pointer;
  background: #3c4a77;
  transition: all .3s ease-out, color .3s ease-out;
}

.inner-form .input-field.third-wrap .btn-search svg {
  width: 16px;
}

.inner-form .input-field.third-wrap .btn-search:hover {
  background: #566daa;
}

.inner-form .input-field.third-wrap .btn-search:focus {
  outline: 0;
  box-shadow: none;
}
</style>

<script>
export default {
  name: 'SearchBar'
}
</script>
