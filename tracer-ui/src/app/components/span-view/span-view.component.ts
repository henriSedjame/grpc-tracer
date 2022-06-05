import {Component, Input, OnInit} from '@angular/core';
import {TraceDto} from "../../grpc/dtos_pb";
import SpanDto = TraceDto.SpanDto;

@Component({
  selector: 'app-span-view',
  templateUrl: './span-view.component.html',
  styleUrls: ['./span-view.component.css']
})
export class SpanViewComponent implements OnInit {

  @Input() span!: SpanDto

  @Input() marginLeft!: number

  @Input() width!: number

  constructor() { }

  ngOnInit(): void {
  }

  public hasError(): boolean {

    return  this.span.getLogsList().some(log => log.getLevel() == 3)

  }

  public hasWarning(): boolean {
      return this.span.getLogsList().some(log => log.getLevel() == 1)
  }
}
