import { DateTime } from 'luxon'
import type { DateTimeFormatOptions } from 'luxon'

export const useDates = () => {
  const fromSeconds = (seconds: number): DateTime => DateTime.fromSeconds(seconds)

  const dateAtStartOfDay = (date: DateTime): DateTime => {
    return date.startOf('day')
  }

  const dateAtStartOfWeek = (date: DateTime): DateTime => {
    return dateAtStartOfDay(date).startOf('week')
  }

  const dateInPastWeek = (date: DateTime): boolean => {
    return date.startOf('day') < dateAtStartOfWeek(DateTime.now())
  }

  const dateInPast = (date: DateTime): boolean => {
    return date.startOf('day') < dateAtStartOfDay(DateTime.now())
  }

  const datesEqual = (date1: DateTime, date2: DateTime): boolean => {
    return dateAtStartOfDay(date1).toMillis() === dateAtStartOfDay(date2).toMillis()
  }

  const dateBefore = (date1: DateTime, date2: DateTime): boolean => {
    return dateAtStartOfDay(date1).toMillis() < dateAtStartOfDay(date2).toMillis()
  }

  const hasExpired = (date: DateTime): boolean => {
    return DateTime.now().toMillis() > date.toMillis()
  }

  const compareStartOfWeek = (candidate: DateTime, startOfWeek: DateTime): boolean => {
    return datesEqual(dateAtStartOfWeek(candidate), startOfWeek)
  }

  const dateInCurrentWeek = (date: DateTime) => {
    return compareStartOfWeek(date, dateAtStartOfWeek(DateTime.now()))
  }

  const daysForWeek = (date: DateTime): DateTime[] => {
    const days = [] as DateTime[]

    for (let i = 0; i < 7; i++) {
      days.push(dateAtStartOfDay(date).plus({ days: i }))
    }

    return days
  }

  const displayDate = (date: DateTime, opts: DateTimeFormatOptions): string => {
    return date.setLocale('no').toLocaleString(opts)
  }

  const longDate = (date: DateTime): string => {
    return displayDate(date, {
      year: 'numeric',
      month: 'long',
      day: 'numeric'
    })
  }

  const shortDate = (date: DateTime): string => {
    return displayDate(date, {
      weekday: 'short',
      day: 'numeric'
    })
  }

  const calendarDate = (date: DateTime): string => {
    return (
      'Uke ' +
      date.weekNumber +
      ':<br />' +
      displayDate(date, {
        day: 'numeric',
        month: 'short'
      }) +
      ' - ' +
      displayDate(date.endOf('week'), {
        day: 'numeric',
        month: 'short'
      })
    )
  }

  const toDateString = (date: DateTime): string => {
    return date.toFormat('yyyy-MM-dd')
  }

  const fromDateString = (date?: string): DateTime => {
    if (!date) {
      return DateTime.now()
    }

    return DateTime.fromFormat(date, 'yyyy-MM-dd')
  }

  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  function dateTimeReviver(key: any, value: any) {
    if (typeof value === 'string' && key === 'date') {
      return dateAtStartOfDay(DateTime.fromISO(value))
    }

    return value
  }

  const isToday = (date: DateTime): boolean => datesEqual(date, DateTime.now())

  return {
    hasExpired,
    dateAtStartOfDay,
    dateAtStartOfWeek,
    dateInPast,
    datesEqual,
    compareStartOfWeek,
    dateInCurrentWeek,
    daysForWeek,
    dateBefore,
    displayDate,
    longDate,
    shortDate,
    calendarDate,
    toDateString,
    fromDateString,
    isToday,
    dateTimeReviver,
    dateInPastWeek,
    fromSeconds
  }
}
