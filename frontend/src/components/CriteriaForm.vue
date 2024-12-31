<template>
  <div class="criteria-form-container">
    <h2 class="title">Configure Criteria</h2>

    <!-- Success Message (Conditional) -->
    <div v-if="showSuccessMessage" class="success-message">
      Criteria saved successfully!
    </div>

    <!-- Input for Identifier -->
    <div class="form-group">
      <label for="identifier">Configuration Identifier:</label>
      <input type="text" v-model="identifier" id="identifier" class="input-field" required />
    </div>

    <!-- Criteria list display -->
    <div v-if="identifier">
      <div v-for="(criteria, index) in criteriaList" :key="index" class="form-group">
        <!-- Criteria card for each item -->
        <div class="criteria-card card">
          <!-- Criteria Type selection inside the card -->
          <div class="input-wrapper">
            <label for="criteriaType">Select Criteria Type:</label>
            <select v-model="criteria.criteriaType" id="criteriaType" required class="input-field">
              <option value="" disabled>Select a criteria</option>
              <option v-for="(label, value) in criteriaOptions" :key="value" :value="value">
                {{ label }}
              </option>
            </select>
          </div>

          <!-- Centered input fields -->
          <div class="input-wrapper">
            <label for="order">Order:</label>
            <input type="number" v-model="criteria.order" class="input-field" required />
          </div>

          <div class="input-wrapper">
            <label for="prefix">Prefix:</label>
            <input type="text" v-model="criteria.prefix" class="input-field" />
          </div>

          <div class="input-wrapper">
            <label for="suffix">Suffix:</label>
            <input type="text" v-model="criteria.suffix" class="input-field" />
          </div>

          <div class="input-wrapper">
            <label for="maxLength">Max Length:</label>
            <input type="number" v-model="criteria.maxLength" class="input-field" />
          </div>

          <div class="input-wrapper">
            <label for="initialValue">Initial Value:</label>
            <input type="number" v-model="criteria.initialValue" class="input-field" />
          </div>

          <div class="input-wrapper">
            <label for="formatLength">Format Length:</label>
            <input type="number" v-model="criteria.formatLength" class="input-field" />
          </div>

          <!-- Add and Remove buttons centered at the bottom of the card -->
          <div class="card-buttons">
            <button type="button" class="add-button" @click="addCriteria">Add Criteria</button>
            <button type="button" class="remove-button" @click="removeCriteria(index)">Remove Criteria</button>
          </div>
        </div>
      </div>

      <!-- Submit button -->
      <button type="submit" class="submit-button" @click="submitForm">Save Criteria</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      identifier: '',
      showSuccessMessage: false,
      criteriaOptions: {
        FIRST_NAME: 'First Name',
        LAST_NAME: 'Last Name',
        BIRTH_DATE: 'Birth Date',
        COUNTER: 'Counter'
      },
      criteriaList: [this.defaultCriteria()]
    };
  },
  methods: {
    defaultCriteria() {
      return {
        criteriaType: '',
        order: 1,
        prefix: '',
        suffix: '',
        maxLength: null,
        initialValue: null,
        formatLength: null
      };
    },

    // Add new criteria to the list
    addCriteria() {
      this.criteriaList.push(this.defaultCriteria());
    },

    // Remove criteria by index
    removeCriteria(index) {
      this.criteriaList.splice(index, 1);
    },

    // Submit form data to the backend
    submitForm() {
      const dataToSubmit = this.criteriaList.map(criteria => ({
        ...criteria
      }));

      // Send the criteria data to the backend
      axios.post(`/api/numbering/configure/${this.identifier}`, dataToSubmit)
          .then(response => {
            console.log('Criteria saved:', response.data);
            this.showSuccessMessage = true;

            this.resetForm();
          })
          .catch(error => {
            console.error('Error saving criteria:', error);
          });
    },
    resetForm() {
      this.identifier = '';
      this.criteriaList = [this.defaultCriteria()];
    }

  }
};
</script>

<style scoped>
/* Container for the form */
.criteria-form-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 2rem;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

/* Title */
.title {
  text-align: center;
  font-size: 1.8rem;
  margin-bottom: 1rem;
  color: #333;
}

/* Form group for inputs */
.form-group {
  margin-bottom: 1.5rem;
}

/* Wrapper for each input field */
.input-wrapper {
  width: 100%;
  margin: 1rem;
}

/* Label styles */
label {
  font-size: 1rem;
  font-weight: bold;
  color: #444;
  margin-bottom: 0.5rem;
  display: block;
}

/* Input field styles */
.input-field {
  width: 90%;
  padding: 0.8rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  color: #333;
  box-sizing: border-box;
}

/* Focus styles for input fields */
.input-field:focus {
  border-color: #4CAF50;
  outline: none;
}

/* Card styles for each criteria */
.card {
  background-color: #ffffff;
  padding: 1.5rem;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  margin-bottom: 1rem;
  text-align: center;
}

/* Button styles for Submit */
.submit-button {
  background-color: #4CAF50;
  color: white;
  padding: 0.8rem 2rem;
  border: none;
  border-radius: 4px;
  font-size: 1.1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 1.5rem;
}

.submit-button:hover {
  background-color: #45a049;
}

/* Add Criteria button */
.add-button {
  background-color: #007bff;
  color: white;
  padding: 0.6em 1.5rem;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-right: 1rem;
}

.add-button:hover {
  background-color: #0056b3;
}

/* Remove Criteria button */
.remove-button {
  background-color: #dc3545;
  color: white;
  padding: 0.6rem 1.5rem;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
}

.remove-button:hover {
  background-color: #c82333;
}

/* Button container styles for add and remove buttons */
.card-buttons {
  display: flex;
  justify-content: center;
  margin-top: 1.5rem;
}

.success-message {
  background-color: #d4edda;
  color: #155724;
  padding: 1rem;
  margin-bottom: 1rem;
  border-radius: 5px;
  text-align: center;
  border: 1px solid #c3e6cb;
}

</style>
