<script setup lang="ts">
import FullCalendar from '@fullcalendar/vue3'
import multiMonthPlugin from '@fullcalendar/multimonth'
import nbLocale from '@fullcalendar/core/locales/nb'
import { type CalendarOptions } from '@fullcalendar/core'
import type { DisplayEvent } from '@/types/DisplayEvent'

const props = defineProps<{
  bookings: DisplayEvent[]
}>()

const calendarOptions = {
  locale: nbLocale,
  plugins: [multiMonthPlugin],
  initialView: 'multiMonthFourMonth',
  multiMonthMaxColumns: 2,
  aspectRatio: 0.8,
  events: props.bookings.map((booking) => {
    return {
      id: booking.id,
      title: booking.hut,
      allDay: true,
      start: booking.start.toJSDate(),
      end: booking.end.toJSDate(),
      classNames: booking.classNames,
      textColor: 'black'
    }
  }),
  views: {
    multiMonthFourMonth: {
      type: 'multiMonth',
      duration: { months: 4 }
    }
  }
} as CalendarOptions
</script>

<template>
  <div class="container">
    <FullCalendar :options="calendarOptions" />
  </div>
</template>
