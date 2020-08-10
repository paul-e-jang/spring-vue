import axios from 'axios'
import errorParser from '@/utils/error-parser'
import eventBus from '@/event-bus'

export default {

  fetchArticles (boardname) {
    return new Promise((resolve, reject) => {
      axios.post('/user').then(({ data }) => {
        resolve(data)
        eventBus.$emit('myDataFetched', data)
        console.log(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
