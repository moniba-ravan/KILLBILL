// load the things we need
var mongoose = require('mongoose');
// define the schema for our user model
var groceriesSchema = mongoose.Schema({
  fruitAndVegetables : 'string',
  superMarket :'string',
  cereals :'string',
  meat: 'string',
  date :'string'
});


// create the model for users and expose it to our app
module.exports = mongoose.model('groceries', groceriesSchema);
