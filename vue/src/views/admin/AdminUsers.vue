<script setup lang="ts">
import UserList from '@/components/admin/UserList.vue'
</script>

<template>
  <div class="container">
    <router-link to="/admin/users/new" class="btn btn-secondary my-2">Add User</router-link>
    <user-list />
  </div>
</template>
