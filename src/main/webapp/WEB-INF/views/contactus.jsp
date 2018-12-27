<style>
font-family: 'Roboto', sans-serif;

.container {
  background: #4dff88;
  width: 900px;
  height: 680px;
  margin: 5% auto;
  position: relative;
}
.container .map {
  width: 45%;
  float: left;
  padding:20px;
}
.container .contact-form {
  width: 53%;
  margin-left: 2%;
  float: left;
}
.container .contact-form .title {
  font-size: 2.5em;
  font-family: "Roboto", sans-serif;
  font-weight: 700;
  color: #242424;
  margin: 5% 8%;
}
.container .contact-form .subtitle {
  font-size: 1.2em;
  font-weight: 400;
  margin: 0 4% 5% 8%;
}
.container .contact-form input,
.container .contact-form textarea {
  width: 330px;
  padding: 3%;
  margin: 2% 8%;
  color: #242424;
  border: 1px solid #B7B7B7;
}
.container .contact-form input::placeholder,
.container .contact-form textarea::placeholder {
  color: #242424;
}
.container .contact-form .btn-send {
  background: #0069c0;
  width: 180px;
  height: 60px;
  color: #FFFFFF;
  font-weight: 700;
  margin: 2% 8%;
  border: none;
}
</style>
<div class="container">
	<div class="map">
		<iframe width="400" height="650" id="gmap_canvas" src="https://maps.google.com/maps?q=one%20niit%2Ccoimbatore&t=&z=15&ie=UTF8&iwloc=&output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
	</div>
	<div class="contact-form">
		<h1 class="title">Contact Us</h1>
		<h2 class="subtitle">We are here assist you.</h2>
		<form action="sendmail" method="post">
			<input type="text" id="name" name="name" placeholder="Your Name" />
			<input type="email" id="e-mail" name="e-mail" placeholder="Your E-mail Adress" />
			<input type="tel" id="phone" name="phone" placeholder="Your Phone Number"/>
			<textarea name="text" id="text" rows="8" placeholder="Your Message"></textarea>
			<button type="submit" class="btn-send">Get a Call Back</button>
		</form>
	</div>
</div>