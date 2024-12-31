<template>
  <div class="home">
    <h1>Welcome to Dynamic Numbering</h1>
    <p>Please select an option:</p>

    <button @click="$router.push('/numbering')">Manage Numbering</button>
  </div>
</template>

<script>
export default {
  name: 'Home'
};
</script>

<style scoped>
.home {
  text-align: center;
  margin-top: 100px;
}
button {
  margin: 20px;
  padding: 15px 30px;
  font-size: 18px;
  cursor: pointer;
}
</style>
