import axios from 'axios'
import errorParser from '@/utils/error-parser'
import eventBus from '@/event-bus'

export default {
  /**
   * Register a new user
   * @param {Object} detail registration detail
   */
  register (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/registrations', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },
  checkAlready (param, value) {
    return new Promise((resolve, reject) => {
      axios.post('/registrations/check/' + param + '/' + value).then(({ data }) => {
        resolve(data)
        console.log(data)
        eventBus.$emit('alreadyExists', data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
