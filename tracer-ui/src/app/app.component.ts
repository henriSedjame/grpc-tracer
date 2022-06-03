import { Component } from '@angular/core';
import { Request } from '@improbable-eng/grpc-web/dist/typings/invoke';
import { grpc } from '@improbable-eng/grpc-web';
import {DashboardService} from "./grpc/services_pb_service";
import {Empty, TraceInfo, TraceRequestEvent, TraceResponseEvent} from "./grpc/dtos_pb";
import Code = grpc.Code;
import Metadata = grpc.Metadata;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'tracer-ui';


  grpcClient!: Request;

  start() {
    let request = new Empty()

    this.grpcClient = grpc.invoke(DashboardService.getTraces,{
      request: request,
      host: "http://localhost:8080",
      onEnd(code: Code, message: string, trailers: Metadata): void {
      },
      onMessage(res: TraceInfo): void {

      },


    } )
  }
}
