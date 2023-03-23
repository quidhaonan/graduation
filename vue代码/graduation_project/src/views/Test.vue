<template>
  <el-popover
    placement="top-start"
    title="Title"
    :width="200"
    trigger="click"
    content="this is content, this is content, this is content"
    id="popover"
  >
    <template #reference>
      <el-button class="m-2">Click to activate</el-button>
    </template>
  </el-popover>
</template>

<script setup>
import { ref } from 'vue'

const visible = ref(false)
</script>

<style scoped>
#popover{
  display: block;
  width: 100px;
  height: 100px;
  margin-top: 100px;
  border: 1px solid red;
}
</style>