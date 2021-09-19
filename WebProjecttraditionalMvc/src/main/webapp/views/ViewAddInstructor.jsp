<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

input[type=text], input[type=password] {
  width: 40%;
  padding: 10px;
  margin: 8px;
  display: inline-block;
  border: 1px solid #ccc;
   margin-left: 390px;
}

button {
  background-color: #9999FF;
  color: white;
  padding: 10px;
  margin: 40px;
  border: none;
  cursor: pointer;
  width: 40%;
  margin-left: 400px;
}
.container {
  padding: 10px;
}
</style>
</head>
<body>

<h2>Add New Instructor</h2>
<form action="/WebProject/AddiInstructor.do" method="post">
  <div class="container">
    <input type="text" placeholder="Enter First Name" name="FName" required><br>
    <input type="text" placeholder="Enter Email" name="email" required><br>
    <input type="password" placeholder="Enter Password" name="psw" required><br>
    <button type="submit">Add</button>
  </div>
</form>
</body>
</html>