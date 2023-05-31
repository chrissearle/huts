<script setup lang="ts">
const props = defineProps<{
  alert: 'primary' | 'secondary' | 'success' | 'danger' | 'warning' | 'info'
  messages?: Readonly<string[]>
}>()

const alertType = `alert-${props.alert}`
</script>

<template>
  <div v-if="props.messages && props.messages.length > 0">
    <div
      class="alert alert-dismissible my-2"
      role="alert"
      :class="alertType"
      v-for="(message, index) in props.messages"
      :key="index"
    >
      {{ message }}
    </div>
  </div>
</template>
