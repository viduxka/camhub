export function truncateNameIfLongerThan ( name, index ) {
  if(name === null || name.length === 0)
    return;
  if(name.length>index){
   return name.slice(0,index)+"..."; 
  }
  return name;    
};
