// load the things we need
var mongoose = require('mongoose');
// define the schema for our user model
var stationerySchema = mongoose.Schema({
  stationery : 'string',
  books :'string',
  date :'string'
});


// create the model for users and expose it to our app
module.exports = mongoose.model('stationery', stationerySchema);
