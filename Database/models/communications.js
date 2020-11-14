// load the things we need
var mongoose = require('mongoose');
// define the schema for our user model
var communicationSchema = mongoose.Schema({
  internet : 'string',
  mobileBill :'string',
  phoneBill :'string',
  date :'string'
});


// create the model for users and expose it to our app
module.exports = mongoose.model('communication', communicationSchema);
