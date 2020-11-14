// load the things we need
var mongoose = require('mongoose');
// define the schema for our user model
var furnitureSchema = mongoose.Schema({
  furniture : 'string',
  cosmetics :'string',
  date :'string'
});


// create the model for users and expose it to our app
module.exports = mongoose.model('furniture', furnitureSchema);
