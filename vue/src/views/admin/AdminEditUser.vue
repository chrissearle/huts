<script setup lang="ts">
import EditUser from '@/components/admin/EditUser.vue'

import { onMounted, computed } from 'vue'
import { useUsers } from '@/stores/useUsers'
import type User from '@/types/User'

const props = defineProps<{
  id: string
}>()

const userStore = useUsers()

const user = computed<User | undefined>(() => {
  return userStore.users.find((u: User) => u.id === +props.id)
})

onMounted(() => {
  userStore.retrieve()
})
</script>

<template>
  <edit-user v-if="user" :user="user" />
</template>
