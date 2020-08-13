export default {
  ParseRefactor (date) {
    let output = 'date is not defined'
    const timestamp = new Date()
    const today = timestamp.getDate()
    const regDateTimestamp = new Date(date)
    const regDate = regDateTimestamp.getDate()
    const regHour = regDateTimestamp.getHours()
    const regMonth = regDateTimestamp.getMonth() + 1
    const regMinutes = regDateTimestamp.getMinutes()
    if (regDate === today) {
      output = regHour + ':' + regMinutes
    } else {
      output = regMonth + '/' + regDate
    }
    return output
  }

}
