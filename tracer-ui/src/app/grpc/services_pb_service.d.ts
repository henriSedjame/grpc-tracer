// package: io.github.hsedjame.grpc.tracing.services
// file: services.proto

import * as services_pb from "./services_pb";
import * as dtos_pb from "./dtos_pb";
import {grpc} from "@improbable-eng/grpc-web";

type DashboardServicegetTraces = {
  readonly methodName: string;
  readonly service: typeof DashboardService;
  readonly requestStream: false;
  readonly responseStream: true;
  readonly requestType: typeof dtos_pb.Empty;
  readonly responseType: typeof dtos_pb.TraceDto;
};

export class DashboardService {
  static readonly serviceName: string;
  static readonly getTraces: DashboardServicegetTraces;
}

export type ServiceError = { message: string, code: number; metadata: grpc.Metadata }
export type Status = { details: string, code: number; metadata: grpc.Metadata }

interface UnaryResponse {
  cancel(): void;
}
interface ResponseStream<T> {
  cancel(): void;
  on(type: 'data', handler: (message: T) => void): ResponseStream<T>;
  on(type: 'end', handler: (status?: Status) => void): ResponseStream<T>;
  on(type: 'status', handler: (status: Status) => void): ResponseStream<T>;
}
interface RequestStream<T> {
  write(message: T): RequestStream<T>;
  end(): void;
  cancel(): void;
  on(type: 'end', handler: (status?: Status) => void): RequestStream<T>;
  on(type: 'status', handler: (status: Status) => void): RequestStream<T>;
}
interface BidirectionalStream<ReqT, ResT> {
  write(message: ReqT): BidirectionalStream<ReqT, ResT>;
  end(): void;
  cancel(): void;
  on(type: 'data', handler: (message: ResT) => void): BidirectionalStream<ReqT, ResT>;
  on(type: 'end', handler: (status?: Status) => void): BidirectionalStream<ReqT, ResT>;
  on(type: 'status', handler: (status: Status) => void): BidirectionalStream<ReqT, ResT>;
}

export class DashboardServiceClient {
  readonly serviceHost: string;

  constructor(serviceHost: string, options?: grpc.RpcOptions);
  getTraces(requestMessage: dtos_pb.Empty, metadata?: grpc.Metadata): ResponseStream<dtos_pb.TraceDto>;
}

