import { computed, ref } from 'vue'

import type Message from '@/types/Message'
import { defineStore } from 'pinia'
import { v4 as uuid } from 'uuid'

export const useMessages = defineStore('messageStore', () => {
  const messages = ref<Message[]>([])

  const setMessages = (msgs: string[]) => {
    messages.value = msgs.map((msg) => {
      return {
        id: uuid(),
        displayed: false,
        hidden: false,
        message: msg
      }
    })
  }

  const display = (id: string) => {
    const idx = messages.value.findIndex((msg) => msg.id === id)

    if (messages.value[idx].displayed === false) {
      messages.value[idx].displayed = true
    } else {
      messages.value[idx].hidden = true
    }

    if (messages.value.filter((msg) => msg.hidden === false).length === 0) {
      messages.value = []
    }
  }

  const nonHiddenMessages = computed<Message[]>(() => {
    return messages.value.filter((msg) => msg.hidden === false)
  })

  return {
    messages: nonHiddenMessages,
    display,
    setMessages
  }
})
