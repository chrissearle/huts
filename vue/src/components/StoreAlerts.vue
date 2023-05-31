<script setup lang="ts">
import { useUsers } from '@/stores/useUsers'
import { useMessages } from '@/stores/useMessages'

import AlertBox from '@/components/AlertBox.vue'
import { watch } from 'vue'
import { useRoute } from 'vue-router'

const userStore = useUsers()
const messageStore = useMessages()

const route = useRoute()

watch(
  () => route.name,
  async () => {
    userStore.clear()
    messageStore.messages.forEach((msg) => messageStore.display(msg.id))
  }
)
</script>

<template>
  <div class="container">
    <alert-box v-if="userStore.error" alert="danger" :messages="userStore.error" />
    <alert-box
      v-if="messageStore.messages"
      alert="primary"
      :messages="messageStore.messages.map((msg) => msg.message)"
    />
  </div>
</template>
