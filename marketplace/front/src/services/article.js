import axios from 'axios'
import errorParser from '@/utils/error-parser'
import eventBus from '@/event-bus'

export default {

  fetchArticles (boardname) {
    return new Promise((resolve, reject) => {
      axios.get('/' + boardname + '/articles').then(({ data }) => {
        resolve(data)
        console.log(data)
        eventBus.$emit('articleLoad', data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },
  fetchReplies (articlecode) {
    return new Promise((resolve, reject) => {
      axios.get('/' + articlecode + '/replies').then(({ data }) => {
        resolve(data)
        console.log(data)
        eventBus.$emit('replyLoad', data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },
  writeArticle (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/writeArticle', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },
  writeReply (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/writeReply', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },
  viewedUpdate (id, how) {
    return new Promise((resolve, reject) => {
      axios.post('/view/' + id + '/' + how).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },
  repliesUpdate (id, how) {
    return new Promise((resolve, reject) => {
      axios.post('/reply/' + id + '/' + how).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
