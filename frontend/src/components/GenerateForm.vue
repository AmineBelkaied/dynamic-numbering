<template>
  <div class="generate-number-container">
    <h2 class="title">Generate Number</h2>

    <!-- Show a message if there are no identifiers -->
    <div v-if="identifiers.length === 0" class="no-identifiers-message">
      <p>No identifiers available. Please configure the identifiers first.</p>
    </div>

    <!-- Show the identifier selection and form only if identifiers are available -->
    <div v-else>
      <div class="form-container">
        <!-- Dropdown for identifiers -->
        <div class="form-group">
          <label for="identifier">Select Identifier:</label>
          <select v-model="selectedIdentifier" id="identifier" required class="input-field">
            <option value="" disabled>Select an identifier</option>
            <option v-for="identifier in identifiers" :key="identifier" :value="identifier">
              {{ identifier }}
            </option>
          </select>
        </div>

        <!-- Form for registrant information -->
        <div v-if="selectedIdentifier" class="form-group">
          <form @submit.prevent="submitForm" class="form">
            <div class="form-row">
              <div class="input-wrapper">
                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" v-model="registrant.firstName" class="input-field" required />
              </div>
              <div class="input-wrapper">
                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" v-model="registrant.lastName" class="input-field" required />
              </div>
            </div>

            <!-- Add margin between birth date input and the previous fields -->
            <div class="form-row" style="margin-top: 1.5rem">
              <div class="input-wrapper center">
                <label for="birthDate">Birth Date:</label>
                <input type="date" id="birthDate" v-model="registrant.birthDate" class="input-field" required />
              </div>
            </div>

            <!-- Add margin between the input and the submit button -->
            <button type="submit" class="submit-button">Generate Number</button>
          </form>
        </div>
      </div>

      <!-- Display generated number -->
      <div v-if="generatedNumber" class="generated-number">
        <h3>Generated Number:</h3>
        <p class="number">{{ generatedNumber }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      selectedIdentifier: '',
      identifiers: [],
      registrant: {
        firstName: '',
        lastName: '',
        birthDate: '',
      },
      generatedNumber: null,
    };
  },
  created() {
    // Fetch available identifiers from the backend
    axios.get('/api/numbering/identifiers')
        .then(response => {
          this.identifiers = response.data;
        })
        .catch(error => {
          console.error('Error fetching identifiers:', error);
        });
  },
  methods: {
    submitForm() {
      // Send the selected identifier and registrant info to the backend
      axios.post(`/api/numbering/generate/${this.selectedIdentifier}`, this.registrant)
          .then(response => {
            // Handle success, e.g., display the generated number
            this.generatedNumber = response.data;
          })
          .catch(error => {
            console.error('Error generating number:', error);
          });
    },
  },
};
</script>

<style scoped>
/* Container for the form */
.generate-number-container {
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

/* Message when there are no identifiers */
.no-identifiers-message {
  text-align: center;
  color: #ff6347;
  font-size: 1.2rem;
  margin-bottom: 1rem;
}

/* Form container */
.form-container {
  margin-top: 2rem;
}

/* Form group for inputs */
.form-group {
  margin-bottom: 1.5rem;
}

/* Row for the first name/last name fields */
.form-row {
  display: flex;
  justify-content: space-between;
  gap: 1rem;
}

/* Wrapper for each input field */
.input-wrapper {
  width: 48%;
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
  width: 100%;
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

/* Submit button styles */
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

/* Displaying the generated number */
.generated-number {
  text-align: center;
  margin-top: 2rem;
}

.number {
  font-size: 1.3rem;
  color: #333;
  font-weight: bold;
}

/* Center birth date */
.input-wrapper.center {
  width: 100%;
  text-align: center;
}

/* Responsive design for smaller screens */
@media (max-width: 600px) {
  .form-row {
    flex-direction: column;
  }
  .input-wrapper {
    width: 100%;
  }
}
</style>
