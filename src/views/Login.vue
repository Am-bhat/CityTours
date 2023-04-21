<template>
  <div id="login">
    <div class='formBox'>
      <form @submit.prevent="login" id="login">
        <br>
        <br>
        <img class="cleveland-logo-login" src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Destination_Cleveland_logo.svg/512px-Destination_Cleveland_logo.svg.png?20211216160402" alt="Script Cleveland"/>
        <h2 >Please sign in to build your adventure!</h2>
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="form-input-group">
          <label for="username" class="usernamePassword">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password" class="usernamePassword">Password</label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <input type="submit" value="Sign in" class="button" id="done" :style="inputStyle" v-on:keyup.enter="login"/>
        <input type="submit" value="Need an account? Sign up" class="button" id="done" :style="inputStyle" v-on:click="register"/>
        <!-- <input type="submit" value="Continue as Guest" class="button" id="done" :style="inputStyle" v-on:click="goToGuestView"/> -->
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
    register() {
      this.$router.push('/register')
    },
    // TODO send to guest view!
    goToGuestView() {
      this.$router.push('/guest')
    }
  }
};
</script>


<style scoped>

@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");

#login {
  display: flex;
  flex-direction: column;
  background-image: url(../../assets/clevelandmap.jpg) ;
  background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  height: 100vh;
  width: 100vw;
  margin: -10px;
}

h1 {
  cursor: default;
  user-select: none;
  font-family: Poppins;
  font-size: 3rem;
}

h2 {
  font-family: Poppins;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.formBox {
  display: flex;
  background-color: white;
  align-items: center;
}

.usernamePassword {
  font-size: 18px;
  font-weight: bold;
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

.button {
  width: 35%;
  cursor: pointer;
  user-select: none;
}

#done {
  width: 300px;
  font-weight: bold;
}

.form-input-group {
  margin-bottom: 1rem;
}

label {
  margin-right: 0.5rem;
}

.welcome {
  text-align: center;
}

p {
  font-family: Poppins;
  background-color: white;
}

#username {
  width: 250px;
}

#password {
  width: 250px;
}

.cleveland-logo-login {
  width: 300px;
}

</style>