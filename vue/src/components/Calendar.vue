<script setup lang="ts">
import { useHuts } from '@/stores/useHuts'
import { useBookings } from '@/stores/useBookings'

import { useDates } from '@/use/useDates'

import { computed, onMounted, ref } from 'vue'
import type { DateTime } from 'luxon'
import type Booking from '@/types/Booking'
import type Hut from '@/types/Hut'

const hutStore = useHuts()
const bookingStore = useBookings()

const { calendarDate, dateAtStartOfWeek, dateAtEndOfWeek, datesEqual } = useDates()

const weeks = computed<DateTime[]>(() => {
  if (bookingStore.bookings.length === 0) {
    return []
  }

  const minDate = dateAtStartOfWeek(
    bookingStore.bookings.reduce((prev, curr) => {
      return curr.fromDate < prev.fromDate ? curr : prev
    }).fromDate
  )

  const maxDate = dateAtEndOfWeek(
    bookingStore.bookings.reduce((prev, curr) => {
      return curr.toDate > prev.toDate ? curr : prev
    }).toDate
  )

  let iterations = maxDate.diff(minDate, 'weeks').toObject().weeks || 0

  let weeks: DateTime[] = [minDate]

  while (iterations > 1) {
    weeks.push(weeks[weeks.length - 1].plus({ days: 7 }))
    iterations -= 1
  }

  return weeks
})

const bookingFor = (hut: Hut, date: DateTime): Booking[] | undefined => {
  if (bookingStore.bookings.length === 0) {
    return undefined
  }

  return bookingStore.bookings.filter((booking) => {
    return booking.hut.id === hut.id && booking.fromDate <= date && booking.toDate >= date
  })
}

const classFor = (date: DateTime, booking: Booking): string => {
  let cssClass = `hut hut${booking.hut.id}`

  if (datesEqual(booking.fromDate, date)) {
    cssClass = `${cssClass} hut_start`
  }

  if (datesEqual(booking.toDate, date)) {
    cssClass = `${cssClass} hut_end`
  }

  return cssClass
}

function* range(start: number, end: number | undefined = undefined, step = 1) {
  if (end === undefined) [end, start] = [start, 0]
  for (let n = start; n < end; n += step) yield n
}

interface DisplayItem {
  booking: Booking
  className: string
}

interface DisplayDay {
  hut: Hut
  bookings: DisplayItem[] | undefined
}

const dataForDate = (date: DateTime): DisplayDay[][] => {
  return Array.from(range(7)).map((n) => {
    const nDate = date.plus({ days: n })

    return hutStore.huts.map((hut) => {
      const bookings = bookingFor(hut, nDate)

      const displays = bookings?.map((booking) => {
        return {
          booking: booking,
          className: classFor(nDate, booking)
        } as DisplayItem
      })

      return {
        hut: hut,
        bookings: displays
      } as DisplayDay
    })
  })
}

onMounted(() => {
  hutStore.retrieve()
  bookingStore.retrieve()
})
</script>

<template>
  <div class="container">
    <div v-if="hutStore.huts.length > 0" class="my-3 keys">
      <div
        v-for="(hut, hutIdx) in hutStore.huts"
        class=""
        :key="hutIdx"
        :class="'hut_start hut_end hut hut_key hut' + hut.id"
      >
        {{ hut.name }}
      </div>
    </div>

    <h1>Kalendar</h1>

    <table class="table table-sm table-bordered table-striped mt-3">
      <thead>
        <tr>
          <th>Dato</th>
          <th>Man</th>
          <th>Tir</th>
          <th>Ons</th>
          <th>Tor</th>
          <th>Fre</th>
          <th>Lør</th>
          <th>Søn</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(week, weekIdx) in weeks" :key="weekIdx">
          <td><span v-html="calendarDate(week)" /></td>
          <td v-for="(data, dataIdx) in dataForDate(week)" :key="dataIdx">
            <div v-for="(display, displayIdx) in data" :key="displayIdx">
              <div
                v-for="(booking, bookingIdx) in display.bookings"
                :key="bookingIdx"
                :class="booking.className"
              >
                {{ display.hut.shortName }}: {{ booking.booking.name }}
              </div>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scope lang="scss">
$bgcolor1: #fcc;
$bgcolor2: #cfc;
$bgcolor3: #ccf;

.keys {
  display: flex;
}

.hut {
  margin-bottom: 3px;
  padding: 0 5px 0 5px;
  border-top: 3px solid;
  border-bottom: 3px solid;
}

.hut_key {
  margin-right: 10px;
  padding: 5px;
}

.hut_start {
  border-left: solid 3px;
}

.hut_end {
  border-right: solid 3px;
}

.hut1 {
  background-color: $bgcolor1;
  border-color: darken($bgcolor1, 20);
}

.hut2 {
  background-color: $bgcolor2;
  border-color: darken($bgcolor2, 20);
}

.hut3 {
  background-color: $bgcolor3;
  border-color: darken($bgcolor3, 100);
}
</style>
