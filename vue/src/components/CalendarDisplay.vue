<script setup lang="ts">
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import nbLocale from '@fullcalendar/core/locales/nb'
import { type CalendarOptions } from '@fullcalendar/core'
import { DateTime } from 'luxon'
import type { DisplayEvent } from '@/types/DisplayEvent'

const props = defineProps<{
  startDate: DateTime
  bookings: DisplayEvent[]
}>()

const calendarOptions = {
  locale: nbLocale,
  weekNumbers: true,
  plugins: [dayGridPlugin],
  initialView: 'dayGridMonth',
  initialDate: props.startDate.toJSDate(),
  aspectRatio: 1.6,
  events: props.bookings.map((booking) => {
    return {
      id: booking.id,
      title: booking.name,
      allDay: true,
      start: booking.start.toJSDate(),
      end: booking.end.toJSDate(),
      classNames: booking.classNames,
      textColor: 'black'
    }
  })
} as CalendarOptions
</script>

<template>
  <div class="container">
    <FullCalendar :options="calendarOptions" />
  </div>
</template>
