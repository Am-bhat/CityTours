<template>
  <div id="register" class="text-center">
    <form @submit.prevent="register" class="container">
      <br>
      <br>
      <img class="cleveland-logo-login" src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Destination_Cleveland_logo.svg/512px-Destination_Cleveland_logo.svg.png?20211216160402" alt="Script Cleveland"/>
      <h2 id="createAccount">Create Account</h2>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group" id="inputUsername">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group" id="inputPassword">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group" id="confirmPasswordContainer">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <input type="submit" value="Create Account" class="button" id="done" :style="inputStyle" v-on:keyup.enter="register"/>
      <input type="submit" value="Already have an account? Log in." class="button" id="done" :style="inputStyle" v-on:click="alreadyHaveAccount"/>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    alreadyHaveAccount(){
      this.$router.push('/login')
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>

@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");

.form-input-group {
  margin-bottom: 1rem;
  font-family: Poppins;
  position: relative;
  text-align: right;
}

label {
  margin-right: 0.5rem;
}

#register {
  display: flex;
  background-image: url(../../assets/clevelandmap.jpg) ;
  background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  height: 100vh;
  width: 100vw;
  justify-content: center;
  margin: -10px;
}

h1 {
  cursor: default;
  user-select: none;
  font-size: 3rem;
}

.container {
  display: flex;
  flex-direction: column;
  width: 70%;
  align-items: center;
}

label {
  font-family: Poppins;
  font-size: 17px;
  font-weight: bold;
}

#confirmPassword {
  width: 250px;
}

input {
  border-radius: 3rem;
  border: none;
  padding: 10px;
  text-align: center;
  outline: none;
  margin: 10px;
  width: 50%;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
  font-weight: 400;
}

input:hover {
  box-shadow: 0px 0px 8px -5px #000000;
}

input:active {
  box-shadow: 0px 0px 8px -5px #000000;
}

button {
  width: 50px;
  cursor: pointer;
  user-select: none;
}

#done {
  width: 300px;
  font-weight: bold;
}

.welcome {
  text-align: center;
}
p {
  font-family: Poppins;
  background-color: white;
}
.button {
  font-family: Poppins, sans-serif;
}

.cleveland-logo-login {
  width: 300px;
}

#username {
  width: 250px;
}

#password {
  width: 250px;
}

</style>


