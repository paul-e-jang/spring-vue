import axios from 'axios'
import errorParser from '@/utils/error-parser'
import eventBus from '@/event-bus'

export default {

  fetchArticles (boardname) {
    return new Promise((resolve, reject) => {
      axios.get('/' + boardname + '/articles').then(({ data }) => {
        resolve(data)
        console.log(data)
        eventBus.$emit('boardview', data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },
  DoWrite (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/write', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
