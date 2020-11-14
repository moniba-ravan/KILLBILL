// load the things we need
var mongoose = require('mongoose');
// define the schema for our user model
var entertainmentSchema = mongoose.Schema({
  restaurantAndCoffeShop : 'string',
  promonade :'string',
  cinema :'string',
  date :'string'
});


// create the model for users and expose it to our app
module.exports = mongoose.model('entertainment', entertainmentSchema);
