<template>
  <div class="container">
    <h1>Numbering Management</h1>
    <button @click="currentView = 'criteria'">Configure Criteria</button>
    <button @click="currentView = 'generate'">Generate Number</button>

    <CriteriaForm v-if="currentView === 'criteria'" />
    <GenerateForm v-if="currentView === 'generate'" />

    <button class="back-btn" @click="$router.push('/')">Back to Home</button>
  </div>
</template>

<script>
import CriteriaForm from '@/components/CriteriaForm.vue';
import GenerateForm from '@/components/GenerateForm.vue';

export default {
  components: {
    CriteriaForm,
    GenerateForm
  },
  data() {
    return {
      currentView: 'criteria'
    };
  }
};
</script>

<style scoped>
.container {
  text-align: center;
  margin-top: 50px;
}
button {
  margin: 10px;
  padding: 12px 24px;
  font-size: 16px;
  cursor: pointer;
}
.back-btn {
  margin-top: 40px;
  background-color: #555;
  color: white;
}
</style>
