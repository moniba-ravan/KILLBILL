// load the things we need
var mongoose = require('mongoose');
// define the schema for our user model
var userSchema = mongoose.Schema({

    username:'string',
    password : 'string',
    age:'string',
    income: 'string',
    clothsArray :'array',
    communicationArray :'array',
    entertainmentArrary :'array',
    furnitureArray :'array',
    groceriesArray :'array',
    stationeryArray :'array',
    transportationArray :'array',
    registerDate: 'string'
  });

// create the model for users and expose it to our app
module.exports = mongoose.model('Users', userSchema);
