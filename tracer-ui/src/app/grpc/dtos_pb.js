// source: dtos.proto
/**
 * @fileoverview
 * @enhanceable
 * @suppress {missingRequire} reports error on implicit type usages.
 * @suppress {messageConventions} JS Compiler reports an error if a variable or
 *     field starts with 'MSG_' and isn't a translatable message.
 * @public
 */
// GENERATED CODE -- DO NOT EDIT!
/* eslint-disable */
// @ts-nocheck

var jspb = require('google-protobuf');
var goog = jspb;
var global = (function() {
  if (this) { return this; }
  if (typeof window !== 'undefined') { return window; }
  if (typeof global !== 'undefined') { return global; }
  if (typeof self !== 'undefined') { return self; }
  return Function('return this')();
}.call(null));

var models_pb = require('./models_pb.js');
goog.object.extend(proto, models_pb);
goog.exportSymbol('proto.io.github.hsedjame.grpc.tracing.dtos.Empty', null, global);
goog.exportSymbol('proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto', null, global);
goog.exportSymbol('proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto', null, global);
/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.io.github.hsedjame.grpc.tracing.dtos.Empty = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, null, null);
};
goog.inherits(proto.io.github.hsedjame.grpc.tracing.dtos.Empty, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  /**
   * @public
   * @override
   */
  proto.io.github.hsedjame.grpc.tracing.dtos.Empty.displayName = 'proto.io.github.hsedjame.grpc.tracing.dtos.Empty';
}
/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.repeatedFields_, null);
};
goog.inherits(proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  /**
   * @public
   * @override
   */
  proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.displayName = 'proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto';
}
/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.repeatedFields_, null);
};
goog.inherits(proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  /**
   * @public
   * @override
   */
  proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.displayName = 'proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto';
}



if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * Optional fields that are not set will be set to undefined.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     net/proto2/compiler/js/internal/generator.cc#kKeyword.
 * @param {boolean=} opt_includeInstance Deprecated. whether to include the
 *     JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @return {!Object}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.Empty.prototype.toObject = function(opt_includeInstance) {
  return proto.io.github.hsedjame.grpc.tracing.dtos.Empty.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Deprecated. Whether to include
 *     the JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.io.github.hsedjame.grpc.tracing.dtos.Empty} msg The msg instance to transform.
 * @return {!Object}
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.io.github.hsedjame.grpc.tracing.dtos.Empty.toObject = function(includeInstance, msg) {
  var f, obj = {

  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.Empty}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.Empty.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.io.github.hsedjame.grpc.tracing.dtos.Empty;
  return proto.io.github.hsedjame.grpc.tracing.dtos.Empty.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.io.github.hsedjame.grpc.tracing.dtos.Empty} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.Empty}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.Empty.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.Empty.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.io.github.hsedjame.grpc.tracing.dtos.Empty.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.io.github.hsedjame.grpc.tracing.dtos.Empty} message
 * @param {!jspb.BinaryWriter} writer
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.io.github.hsedjame.grpc.tracing.dtos.Empty.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
};



/**
 * List of repeated fields within this message type.
 * @private {!Array<number>}
 * @const
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.repeatedFields_ = [5];



if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * Optional fields that are not set will be set to undefined.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     net/proto2/compiler/js/internal/generator.cc#kKeyword.
 * @param {boolean=} opt_includeInstance Deprecated. whether to include the
 *     JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @return {!Object}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.prototype.toObject = function(opt_includeInstance) {
  return proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Deprecated. Whether to include
 *     the JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto} msg The msg instance to transform.
 * @return {!Object}
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.toObject = function(includeInstance, msg) {
  var f, obj = {
    correlationid: jspb.Message.getFieldWithDefault(msg, 1, ""),
    label: jspb.Message.getFieldWithDefault(msg, 2, ""),
    duration: jspb.Message.getFieldWithDefault(msg, 3, 0),
    closed: jspb.Message.getBooleanFieldWithDefault(msg, 4, false),
    spansList: jspb.Message.toObjectList(msg.getSpansList(),
    proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.toObject, includeInstance)
  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto;
  return proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    case 1:
      var value = /** @type {string} */ (reader.readString());
      msg.setCorrelationid(value);
      break;
    case 2:
      var value = /** @type {string} */ (reader.readString());
      msg.setLabel(value);
      break;
    case 3:
      var value = /** @type {number} */ (reader.readInt32());
      msg.setDuration(value);
      break;
    case 4:
      var value = /** @type {boolean} */ (reader.readBool());
      msg.setClosed(value);
      break;
    case 5:
      var value = new proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto;
      reader.readMessage(value,proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.deserializeBinaryFromReader);
      msg.addSpans(value);
      break;
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto} message
 * @param {!jspb.BinaryWriter} writer
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
  f = message.getCorrelationid();
  if (f.length > 0) {
    writer.writeString(
      1,
      f
    );
  }
  f = message.getLabel();
  if (f.length > 0) {
    writer.writeString(
      2,
      f
    );
  }
  f = message.getDuration();
  if (f !== 0) {
    writer.writeInt32(
      3,
      f
    );
  }
  f = message.getClosed();
  if (f) {
    writer.writeBool(
      4,
      f
    );
  }
  f = message.getSpansList();
  if (f.length > 0) {
    writer.writeRepeatedMessage(
      5,
      f,
      proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.serializeBinaryToWriter
    );
  }
};



/**
 * List of repeated fields within this message type.
 * @private {!Array<number>}
 * @const
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.repeatedFields_ = [4];



if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * Optional fields that are not set will be set to undefined.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     net/proto2/compiler/js/internal/generator.cc#kKeyword.
 * @param {boolean=} opt_includeInstance Deprecated. whether to include the
 *     JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @return {!Object}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.prototype.toObject = function(opt_includeInstance) {
  return proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Deprecated. Whether to include
 *     the JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto} msg The msg instance to transform.
 * @return {!Object}
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.toObject = function(includeInstance, msg) {
  var f, obj = {
    label: jspb.Message.getFieldWithDefault(msg, 1, ""),
    start: jspb.Message.getFieldWithDefault(msg, 2, 0),
    duration: jspb.Message.getFieldWithDefault(msg, 3, 0),
    logsList: jspb.Message.toObjectList(msg.getLogsList(),
    models_pb.Log.toObject, includeInstance)
  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto;
  return proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    case 1:
      var value = /** @type {string} */ (reader.readString());
      msg.setLabel(value);
      break;
    case 2:
      var value = /** @type {number} */ (reader.readInt32());
      msg.setStart(value);
      break;
    case 3:
      var value = /** @type {number} */ (reader.readInt32());
      msg.setDuration(value);
      break;
    case 4:
      var value = new models_pb.Log;
      reader.readMessage(value,models_pb.Log.deserializeBinaryFromReader);
      msg.addLogs(value);
      break;
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto} message
 * @param {!jspb.BinaryWriter} writer
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
  f = message.getLabel();
  if (f.length > 0) {
    writer.writeString(
      1,
      f
    );
  }
  f = message.getStart();
  if (f !== 0) {
    writer.writeInt32(
      2,
      f
    );
  }
  f = message.getDuration();
  if (f !== 0) {
    writer.writeInt32(
      3,
      f
    );
  }
  f = message.getLogsList();
  if (f.length > 0) {
    writer.writeRepeatedMessage(
      4,
      f,
      models_pb.Log.serializeBinaryToWriter
    );
  }
};


/**
 * optional string label = 1;
 * @return {string}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.prototype.getLabel = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 1, ""));
};


/**
 * @param {string} value
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto} returns this
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.prototype.setLabel = function(value) {
  return jspb.Message.setProto3StringField(this, 1, value);
};


/**
 * optional int32 start = 2;
 * @return {number}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.prototype.getStart = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 2, 0));
};


/**
 * @param {number} value
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto} returns this
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.prototype.setStart = function(value) {
  return jspb.Message.setProto3IntField(this, 2, value);
};


/**
 * optional int32 duration = 3;
 * @return {number}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.prototype.getDuration = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 3, 0));
};


/**
 * @param {number} value
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto} returns this
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.prototype.setDuration = function(value) {
  return jspb.Message.setProto3IntField(this, 3, value);
};


/**
 * repeated io.github.hsedjame.grpc.tracing.models.Log logs = 4;
 * @return {!Array<!proto.io.github.hsedjame.grpc.tracing.models.Log>}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.prototype.getLogsList = function() {
  return /** @type{!Array<!proto.io.github.hsedjame.grpc.tracing.models.Log>} */ (
    jspb.Message.getRepeatedWrapperField(this, models_pb.Log, 4));
};


/**
 * @param {!Array<!proto.io.github.hsedjame.grpc.tracing.models.Log>} value
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto} returns this
*/
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.prototype.setLogsList = function(value) {
  return jspb.Message.setRepeatedWrapperField(this, 4, value);
};


/**
 * @param {!proto.io.github.hsedjame.grpc.tracing.models.Log=} opt_value
 * @param {number=} opt_index
 * @return {!proto.io.github.hsedjame.grpc.tracing.models.Log}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.prototype.addLogs = function(opt_value, opt_index) {
  return jspb.Message.addToRepeatedWrapperField(this, 4, opt_value, proto.io.github.hsedjame.grpc.tracing.models.Log, opt_index);
};


/**
 * Clears the list making it empty but non-null.
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto} returns this
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto.prototype.clearLogsList = function() {
  return this.setLogsList([]);
};


/**
 * optional string correlationId = 1;
 * @return {string}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.prototype.getCorrelationid = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 1, ""));
};


/**
 * @param {string} value
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto} returns this
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.prototype.setCorrelationid = function(value) {
  return jspb.Message.setProto3StringField(this, 1, value);
};


/**
 * optional string label = 2;
 * @return {string}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.prototype.getLabel = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 2, ""));
};


/**
 * @param {string} value
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto} returns this
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.prototype.setLabel = function(value) {
  return jspb.Message.setProto3StringField(this, 2, value);
};


/**
 * optional int32 duration = 3;
 * @return {number}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.prototype.getDuration = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 3, 0));
};


/**
 * @param {number} value
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto} returns this
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.prototype.setDuration = function(value) {
  return jspb.Message.setProto3IntField(this, 3, value);
};


/**
 * optional bool closed = 4;
 * @return {boolean}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.prototype.getClosed = function() {
  return /** @type {boolean} */ (jspb.Message.getBooleanFieldWithDefault(this, 4, false));
};


/**
 * @param {boolean} value
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto} returns this
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.prototype.setClosed = function(value) {
  return jspb.Message.setProto3BooleanField(this, 4, value);
};


/**
 * repeated SpanDto spans = 5;
 * @return {!Array<!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto>}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.prototype.getSpansList = function() {
  return /** @type{!Array<!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto>} */ (
    jspb.Message.getRepeatedWrapperField(this, proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto, 5));
};


/**
 * @param {!Array<!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto>} value
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto} returns this
*/
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.prototype.setSpansList = function(value) {
  return jspb.Message.setRepeatedWrapperField(this, 5, value);
};


/**
 * @param {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto=} opt_value
 * @param {number=} opt_index
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto}
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.prototype.addSpans = function(opt_value, opt_index) {
  return jspb.Message.addToRepeatedWrapperField(this, 5, opt_value, proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.SpanDto, opt_index);
};


/**
 * Clears the list making it empty but non-null.
 * @return {!proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto} returns this
 */
proto.io.github.hsedjame.grpc.tracing.dtos.TraceDto.prototype.clearSpansList = function() {
  return this.setSpansList([]);
};


goog.object.extend(exports, proto.io.github.hsedjame.grpc.tracing.dtos);
