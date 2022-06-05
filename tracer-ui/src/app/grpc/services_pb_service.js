// package: io.github.hsedjame.grpc.tracing.services
// file: services.proto

var services_pb = require("./services_pb");
var dtos_pb = require("./dtos_pb");
var grpc = require("@improbable-eng/grpc-web").grpc;

var DashboardService = (function () {
  function DashboardService() {}
  DashboardService.serviceName = "io.github.hsedjame.grpc.tracing.services.DashboardService";
  return DashboardService;
}());

DashboardService.getTraces = {
  methodName: "getTraces",
  service: DashboardService,
  requestStream: false,
  responseStream: true,
  requestType: dtos_pb.Empty,
  responseType: dtos_pb.TraceDto
};

exports.DashboardService = DashboardService;

function DashboardServiceClient(serviceHost, options) {
  this.serviceHost = serviceHost;
  this.options = options || {};
}

DashboardServiceClient.prototype.getTraces = function getTraces(requestMessage, metadata) {
  var listeners = {
    data: [],
    end: [],
    status: []
  };
  var client = grpc.invoke(DashboardService.getTraces, {
    request: requestMessage,
    host: this.serviceHost,
    metadata: metadata,
    transport: this.options.transport,
    debug: this.options.debug,
    onMessage: function (responseMessage) {
      listeners.data.forEach(function (handler) {
        handler(responseMessage);
      });
    },
    onEnd: function (status, statusMessage, trailers) {
      listeners.status.forEach(function (handler) {
        handler({ code: status, details: statusMessage, metadata: trailers });
      });
      listeners.end.forEach(function (handler) {
        handler({ code: status, details: statusMessage, metadata: trailers });
      });
      listeners = null;
    }
  });
  return {
    on: function (type, handler) {
      listeners[type].push(handler);
      return this;
    },
    cancel: function () {
      listeners = null;
      client.close();
    }
  };
};

exports.DashboardServiceClient = DashboardServiceClient;

